package service;

import bl.SessionUtil;
import dao.CarTypeDAO;
import entity.Car;
import entity.CarType;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CarTypeService extends SessionUtil  implements CarTypeDAO {
    @Override
    public void add(CarType carType) {
        openTransactionSession();

        Session session = getSession();
        session.save(carType);

        closeTransactionSession();
    }

    @Override
    public List<CarType> getAllTypes() {
        openTransactionSession();

        try{
            Session session = getSession();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CarType> cq = cb.createQuery(CarType.class);
            Root<CarType> rootEntry = cq.from(CarType.class);
            CriteriaQuery<CarType> all = cq.select(rootEntry);

            TypedQuery<CarType> allQuery = session.createQuery(all);


            return allQuery.getResultList();
        }
        finally {
            closeTransactionSession();
        }
    }
}
