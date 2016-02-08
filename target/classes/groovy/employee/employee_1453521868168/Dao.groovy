package groovy.employee.employee_1453521868168

import com.oshippa.server.groovy.dao.GBaseDao
import groovy.sql.Sql
import org.hibernate.Query
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import groovy.sql.GroovyRowResult
@Repository
@Transactional
class employee implements GBaseDao{

@Autowired Sql sql;
@Override
void insert( Map params) {
String insert = "insert into employee_1453521868168 (name,myid) values(:name,:myid)";
Map map = new HashMap();
map.put("name",params.get("name"));
map.put("myid",params.get("myid"));
this.sql.execute(map,insert)
}
@Override
Map get(int id){
GroovyRowResult result =  this.sql.firstRow("select * from employee_1453521868168 where id="+id);
if(result!=null){
Map map = new HashMap();
map.put("id",id);
map.put("name",result.get("name"));
map.put("myid",result.get("myid"));
return map;
}else{
return null;
}

}
@Override
void update(int id, Map params) {
String updateSql = "update employee_1453521868168 set  (name,myid) values(:name,:myid) where id=:id";
Map map = new HashMap();
map.put("id",id);
map.put("name",params.get("name"));
map.put("myid",params.get("myid"));
this.sql.execute(map,updateSql)
}

@Override
void delete(int id) {
String deleteSql = "";
}
}