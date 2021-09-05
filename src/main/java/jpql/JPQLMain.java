package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JPQLMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        try {
            Member member = new Member();
            member.setId(1L);
            member.setAge(30);
            member.setName("yunho");
            em.persist(member);

            TypedQuery<Member> query = em.createQuery("select m from Member m where m.name = :name", Member.class);
            query.setParameter("name", "yunho");
            Member singleResult = query.getSingleResult();
            System.out.println("singleResult = " + singleResult.getName() );
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
