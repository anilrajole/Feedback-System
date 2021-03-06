package Admin;

import DB.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Anil on 29/01/2018
 */
@Path("/student_services")
@WebService
public class Student_Service {
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("add")
    public String add(@FormParam("param1")String studjson,@FormParam("param2") int cl, @FormParam("param3") int dv)
    {
        Session session= Global.getSession();
        Transaction transaction=session.beginTransaction();
        try
        {
            Object object=null;
            JSONArray arrayObj=null;
            JSONParser jsonParser=new JSONParser();
            object=jsonParser.parse(studjson);
            arrayObj=(JSONArray) object;


            CSClass csClass= (CSClass) session.createQuery("from CSClass c where c.id= :id").setParameter("id",cl).uniqueResult();
            Division division= (Division) session.createQuery("from Division d where d.id=:id").setParameter("id",dv).uniqueResult();

            for (int i = 0; i < arrayObj.size(); i++)
            {
                JSONObject jsonObj = (JSONObject) arrayObj.get(i);
                if(jsonObj!=null)
                {
                    Student s=new Student();
                    String nme= (String) jsonObj.get("roll");
                    s.setRoll(Integer.parseInt(nme));
                    s.setName((String) jsonObj.get("name"));
                    s.setCSClass(csClass);
                    s.setDivision(division);
                    session.persist(s);

                }
            }
            transaction.commit();
            session.close();
            return "1";
        }
        catch (Exception e)
        {
            transaction.commit();
            session.close();
            return ""+e+"";
        }
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("viewDivisionWise")
    public List viewDivisionWise(@FormParam("param1")int div)
    {
        Session session1= Global.getSession();
        Transaction t=session1.beginTransaction();
        try
        {

            java.util.List<Student> tlist=session1.createQuery("from Student s where s.division.id=:id").setParameter("id",div).list();
            List list=new ArrayList();
            for(Iterator iterator = tlist.iterator(); iterator.hasNext();)
            {
                Student student= (Student) iterator.next();
                List list1=new ArrayList();
                list1.add(student.getId());
                list1.add(student.getName());
                list1.add(student.getRoll());
                list.add(list1);
            }
            t.commit();
            session1.close();
            return list;
        }
        catch (Exception e)
        {
            t.commit();
            session1.close();
            return Collections.singletonList("" + e + "");
        }
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("viewDivisionWise1")
    public List viewDivisionWise1(@FormParam("param1")int div)
    {
        Session session1= Global.getSession();
        Transaction t=session1.beginTransaction();
        try
        {
//            Global.reload();

            java.util.List<Student> tlist=session1.createQuery("from Student s where s.division.id=:id").setParameter("id",div).list();
            List list=new ArrayList();
            for(Iterator iterator = tlist.iterator(); iterator.hasNext();)
            {
                Student student= (Student) iterator.next();
                List list1=new ArrayList();
                list1.add(student.getId());
                list1.add(student.getName());
                list1.add(student.getRoll());
                list.add(list1);
            }
            t.commit();
            session1.close();
            return list;
        }
        catch (Exception e)
        {
            t.commit();
            session1.close();
            return Collections.singletonList("" + e + "");
        }
    }

    @GET
    @Path("viewAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List viewAll()
    {
        Session session= Global.getSession();
        Transaction t=session.beginTransaction();
        java.util.List<Student> slist=session.createQuery("select s.id,s.name,s.roll,s.CSClass,s.division from Student s").list();
        t.commit();
        session.close();
        return slist;
    }
    @POST
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public List  search(@FormParam("param1") String sname,@FormParam("param2")int div)
    {
        Session session = Global.getSession();
        Transaction transaction=session.beginTransaction();
        try {

            java.util.List<Student> tlist=session.createQuery("from Student s where s.division.id=:id1 and s.name like :id ").setParameter("id","%"+sname+"%").setParameter("id1",div).list();
            List list=new ArrayList();
            for(Iterator iterator = tlist.iterator(); iterator.hasNext();)
            {
                Student student= (Student) iterator.next();
                List list1=new ArrayList();
                list1.add(student.getId());
                list1.add(student.getName());
                list1.add(student.getRoll());
                list.add(list1);
            }
            transaction.commit();
            session.close();
            return list;
        }
        catch (Exception e)
        {
            transaction.commit();
            session.close();
            return null;
        }
    }
    @POST
    @Path("searchById")
    @Produces(MediaType.TEXT_PLAIN)
    public String searchById(@FormParam("param1") int sid)
    {
        Session session = Global.getSession();
        Transaction transaction=session.beginTransaction();
        try {

            Student student= (Student) session.createQuery("from Student s where s.id=:sid").setParameter("sid",sid).uniqueResult();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("1",student.getId());
            jsonObject.put("2",student.getName());
            jsonObject.put("3",student.getRoll());
            jsonObject.put("4",student.getCSClass().getId());
            jsonObject.put("5",student.getDivision().getId());
            transaction.commit();
            session.close();
            return String.valueOf(jsonObject);
        }
        catch (Exception e)
        {
            transaction.commit();
            session.close();
            return "E";
        }
    }
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("delete")
    public String delete(@FormParam("param1") int tid)
    {
//        try
//        {
            Session session= Global.getSession();
            Transaction t=session.beginTransaction();
            Student student= session.get(Student.class,tid);
            if (student==null) {

                return "0";
            }
            else
            {
                Query query=session.createQuery("delete from LSmcq s where s.student.id=:id").setParameter("id",tid);
                query.executeUpdate();
                Query query1=session.createQuery("delete from LSrate s where s.student.id=:id").setParameter("id",tid);
                query1.executeUpdate();
                Query query2=session.createQuery("delete from LScomment s where s.student.id=:id").setParameter("id",tid);
                query2.executeUpdate();

                Query query3=session.createQuery("delete from SSmcq s where s.student.id=:id").setParameter("id",tid);
                query3.executeUpdate();
                Query query4=session.createQuery("delete from SSrate s where s.student.id=:id").setParameter("id",tid);
                query4.executeUpdate();
                Query query5=session.createQuery("delete from SScomment s where s.student.id=:id").setParameter("id",tid);
                query5.executeUpdate();

                User user= (User) session.createQuery("from User s where s.id=:id").setParameter("id",""+student.getId()).uniqueResult();
                if(user!=null) {
                    Query query6 = session.createQuery("delete from Notification s where s.user.uid=:id").setParameter("id", user.getUid());
                    query6.executeUpdate();
                    Query query7=session.createQuery("delete from User s where s.uid=:id").setParameter("id",user.getUid());
                    query7.executeUpdate();
//                    session.delete(user);
                }
                Query query8=session.createQuery("delete from Student s where s.id=:id").setParameter("id",student.getId());
                query8.executeUpdate();
//                session.delete(student);
            }
            t.commit();
            session.close();
            return "1";
//        }
//        catch (Exception e)
//        {
//            return String.valueOf(e);
//        }
    }
}