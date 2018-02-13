package Admin;

import DB.CSClass;
import DB.Course;
import DB.Subject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by Anil on 31/01/2018
 */
@Path("/class_services")
@WebService
public class Class_Service
{
    @GET
    @Path("viewAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CSClass> viewAll()
    {
        Session session= DB.Global.getSession();
        Transaction t=session.beginTransaction();
        java.util.List<CSClass> tlist=session.createQuery("select s.id,s.name,s.course from CSClass s ").list();
        t.commit();
        session.close();
        return tlist;
    }
    @POST
    @Path("getCourseWise")
    @Produces(MediaType.APPLICATION_JSON)
    public List viewAll(@FormParam("param1") int cid)
    {
        Session session= DB.Global.getSession();
        Transaction t=session.beginTransaction();
        java.util.List<CSClass> tlist=session.createQuery("from CSClass s where s.course.id=:id").setParameter("id",cid).list();
        List list=new ArrayList();
        for(Iterator iterator = tlist.iterator(); iterator.hasNext();)
        {
            CSClass csClass= (CSClass) iterator.next();
            List list1=new ArrayList();
            list1.add(csClass.getId());
            list1.add(csClass.getName());
            list.add(list1);
        }
        t.commit();
        session.close();
        return list;
    }
}