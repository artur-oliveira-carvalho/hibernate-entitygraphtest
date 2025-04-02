# Hibernate EntityGraph Test

In Hibernate 5.6.15.Final, I have used EntityGraph to load read-only entities in one query. When I have updated to hibernate 6.6.12.Final some entity queries are performing twice as many queries.

## Hibernate 5 Test Case SQLs:
Hibernate: insert into person (owner, federal_registration, name) values (?, ?, ?)
Hibernate: insert into establishment (owner, person_id) values (?, ?)
Hibernate: select establishm0_.id as id1_2_0_, person1_.id as id1_3_1_, establishm0_.owner as owner2_2_0_, establishm0_.person_id as person_i3_2_0_, person1_.owner as owner2_3_1_, person1_.federal_registration as federal_3_3_1_, person1_.name as name4_3_1_ from establishment establishm0_ left outer join person person1_ on establishm0_.person_id=person1_.id where establishm0_.id=?
Hibernate: select access0_.id as id1_0_0_, access0_.owner as owner2_0_0_, access0_.person_id as person_i3_0_0_ from access access0_ where access0_.person_id=?
Hibernate: select collaborat0_.id as id1_4_0_, collaborat0_.owner as owner2_4_0_, collaborat0_.person_id as person_i3_4_0_ from supplier collaborat0_ where collaborat0_.person_id=?
Hibernate: select customer0_.id as id1_1_0_, customer0_.owner as owner2_1_0_, customer0_.person_id as person_i3_1_0_ from customer customer0_ where customer0_.person_id=?
Hibernate: select supplier0_.id as id1_4_0_, supplier0_.owner as owner2_4_0_, supplier0_.person_id as person_i3_4_0_ from supplier supplier0_ where supplier0_.person_id=?

## Hibernate 6 Test Case SQLs:
Hibernate: insert into person (federal_registration,name,owner) values (?,?,?)
Hibernate: insert into establishment (owner,person_id) values (?,?)
Hibernate: select e1_0.id,e1_0.owner,e1_0.person_id,p1_0.id,p1_0.federal_registration,p1_0.name,p1_0.owner from establishment e1_0 join person p1_0 on p1_0.id=e1_0.person_id where e1_0.id=?
Hibernate: select a1_0.id,a1_0.owner,a1_0.person_id from access a1_0 where a1_0.person_id=?
Hibernate: select c1_0.id,c1_0.owner,c1_0.person_id from supplier c1_0 where c1_0.person_id=?
Hibernate: select c1_0.id,c1_0.owner,c1_0.person_id from customer c1_0 where c1_0.person_id=?
Hibernate: select e1_0.id,e1_0.owner,e1_0.person_id,p1_0.id,p1_0.federal_registration,p1_0.name,p1_0.owner from establishment e1_0 join person p1_0 on p1_0.id=e1_0.person_id where e1_0.person_id=?
Hibernate: select a1_0.id,a1_0.owner,a1_0.person_id from access a1_0 where a1_0.person_id=?
Hibernate: select c1_0.id,c1_0.owner,c1_0.person_id from supplier c1_0 where c1_0.person_id=?
Hibernate: select c1_0.id,c1_0.owner,c1_0.person_id from customer c1_0 where c1_0.person_id=?
Hibernate: select s1_0.id,s1_0.owner,s1_0.person_id from supplier s1_0 where s1_0.person_id=?
Hibernate: select s1_0.id,s1_0.owner,s1_0.person_id from supplier s1_0 where s1_0.person_id=?
