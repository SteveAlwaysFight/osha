package groovy.new_company.new_company_1453513198286

import com.oshippa.server.helper.groovy.basedao.GBaseDao
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository()
@Transactional
class new_company implements GBaseDao {

    @Autowired
    Sql sql;

    @Override
    boolean insert(Map params, String userId) {
        String insert = "insert into new_company_1453513198286 (name,age,userId) values(:name,:age,:userId)";
        Map map = new HashMap();
        map.put("name", params.get("name"));
        map.put("age", params.get("age"));
        map.put("userId", userId);
        return this.sql.execute(map, insert);
    }

    @Override
    Map get(int id, String userId) {
        GroovyRowResult result = this.sql.firstRow("select * from new_company_1453513198286 where deleted=false and id='" + id + "' and userId='" + userId + "'");
        return result;
    }

    @Override
    boolean update(int id, Map params, String userId) {
        String updateSql = "update new_company_1453513198286 set  (name,age) values(:name,:age) where id=:id and userId=:userId";
        Map map = new HashMap();
        map.put("id", id);
        map.put("userId", userId);
        map.put("name", params.get("name"));
        map.put("age", params.get("age"));
        return this.sql.executeUpdate(map, updateSql)
    }

    @Override
    boolean delete(int id, String userId) {
        String deleteSql = "update new_company_1453513198286 set deleted=true where userId=:userId and id =:id";
        Map map = new HashMap();
        map.put("id", id);
        map.put("userId", userId);
        return result = this.sql.executeUpdate(map, deleteSql);
    }

    @Override
    List list(String userId) {
        String listSql = "select * from new_company_1453513198286 where deleted=false and  userId='" + userId + "'";
        return sql.rows(listSql);
    }
}