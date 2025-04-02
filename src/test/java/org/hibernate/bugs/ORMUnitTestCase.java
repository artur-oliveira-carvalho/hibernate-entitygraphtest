/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hibernate.bugs;

import com.tartigrado.models.*;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.jpa.SpecHints;
import org.hibernate.testing.orm.junit.*;
import org.junit.jupiter.api.Test;

@DomainModel(
        annotatedClasses = {
                Access.class,
                Collaborator.class,
                Customer.class,
                Establishment.class,
                LongModel.class,
                Person.class,
                Supplier.class
        }
)
@ServiceRegistry(
        settings = {
                @Setting(name = AvailableSettings.SHOW_SQL, value = "true"),
                @Setting(name = AvailableSettings.FORMAT_SQL, value = "false"),
                @Setting(name = AvailableSettings.GENERATE_STATISTICS, value = "true"),
        }
)
@SessionFactory
class ORMUnitTestCase {

    @Test
    void testShouldNotPerformManyQueriesWithEntityGraph(SessionFactoryScope scope) throws Exception {
        scope.inTransaction(session -> {

            Person person = Person.builder().name("Test").owner(0L).build();
            session.persist(person);

            Establishment establishment = Establishment.builder().person(person).build();
            session.persist(establishment);

            session.clear();

            CriteriaBuilder builder = session.getCriteriaBuilder();

            EntityGraph<?> graph = session.getEntityGraph("graph.FetchFullEstablishments");
            CriteriaQuery<Establishment> criteriaQuery = builder.createQuery(Establishment.class);
            Root<Establishment> establishmentRoot = criteriaQuery.from(Establishment.class);
            criteriaQuery = criteriaQuery
                    .select(establishmentRoot)
                    .where(builder.equal(establishmentRoot.get("id"), builder.parameter(Long.class, "id")));

            Establishment establishmentResult = session.createQuery(criteriaQuery)
                    .setParameter("id", establishment.getId())
                    .setHint(SpecHints.HINT_SPEC_FETCH_GRAPH, graph)
                    .getResultList()
                    .stream()
                    .findAny()
                    .orElseThrow();

            Long customerId = establishmentResult.getCustomerId();
            Long establismentId = establishmentResult.getEstablishmentId();
            Long supplierId = establishmentResult.getSupplierId();
            Long collaboratorId = establishmentResult.getCollaboratorId();
            Long accessId = establishmentResult.getAccessId();

        });
    }
}
