package com.onwl007.apiblog.service;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import org.bson.Document;
import com.onwl007.apiblog.domain.Article;
import com.onwl007.apiblog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author onwl007@126.com
 * @date 2018/4/22 16:23
 * @desc
 */
@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    public Page<Article> pageArticles(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    /**
     * 查询所有文章按照创建时间降序排列
     *
     * @return
     */
    public List<Article> listArticles() {
        return articleRepository.findAllByOrderByCreateAtDesc();
    }

    /**
     * 根据id查询文章
     *
     * @return
     */
    public Article getArticleById(String id) {
        return articleRepository.findArticleById(id);
    }

    /**
     * 对文章标题进行模糊查询
     *
     * @param key
     * @param pageable
     * @return
     */
    public Page<Article> getArticleByTitleLike(String key, Pageable pageable) {
        return articleRepository.findArticleByTitleLike(key, pageable);
    }

    /**
     * 查询关联同一个标签下的所有文章
     *
     * @param id
     * @param pageable
     * @return
     */
    public Page<Article> getArticleByTagId(String id, Pageable pageable) {
        return articleRepository.findAllByTag_Id(id, pageable);
    }

    /**
     * 查询关联同一个分类下的所有文章
     *
     * @param id
     * @param pageable
     * @return
     */
    public Page<Article> getArticleByCategoryId(String id, Pageable pageable) {
        return articleRepository.findAllByCategory_Id(id, pageable);
    }

    /**
     * 创建文章或更新文章
     *
     * @param article
     */
    public void createArticle(Article article) {
        articleRepository.save(article);
    }

    /**
     * 删除文章
     */
    public void deleteArticle(String id) {
        articleRepository.deleteById(id);
    }

    /**
     * @param sort
     * @return
     */
    public List<Article> listHotArticle(Sort sort) {
        return articleRepository.findAll(sort);
    }

    /**
     * 查询归档文章
     */
    public void archivesArticle() {

        /**String matchStr = "{ $match: { state: 1 } }";
        BasicDBObject match = (BasicDBObject) JSON.parse(matchStr);
        String sortStr = "{ $sort: { createAt: 1 } }";
        BasicDBObject sort = (BasicDBObject) JSON.parse(sortStr);
        String projectStr = "{$project:{year:{$year:'$createAt'},month:{$month:'$createAt'},title:1,createAt:1}}";
        BasicDBObject project = (BasicDBObject) JSON.parse(projectStr);
        String groupStr = "{$group:{_id:{year:'$year',month:'$month'}},articles:{$push:{title:'$title',_id:'$_id',createAt:'createAt'}}}}";
        BasicDBObject group = (BasicDBObject) JSON.parse(groupStr);
        List<BasicDBObject> list = new ArrayList<BasicDBObject>();
        list.add(match);
        list.add(sort);
        list.add(project);
        list.add(group);*/
        /**AggregationOutput output= mongoTemplate.getDb().getCollection("article");
        Iterator<DBObject> iter= output.results().iterator();
        DBObject result=null;
        while (iter.hasNext()){
            result=iter.next();
            break;
        }*/
        //System.out.println("******   "+result.toString());

        /**AggregateIterable<org.bson.Document> docIterable = mongoTemplate.getCollection("article").aggregate(list);
        MongoCursor<org.bson.Document> cursor = docIterable.iterator();
        while (cursor.hasNext()) {
            org.bson.Document doc = cursor.next();
            System.out.println(doc.getInteger("count"));
        }
        System.out.println(docIterable.toString());*/

        /**String matchStr = "{ $match: { state: 1 } }";
        DBObject match = (DBObject) JSON.parse(matchStr);
        String sortStr = "{ $sort: { createAt: 1 } }";
        DBObject sort = (DBObject) JSON.parse(sortStr);
        String projectStr = "{$project:{year:{$year:'$createAt'},month:{$month:'$createAt'},title:1,createAt:1}}";
        DBObject project = (DBObject) JSON.parse(projectStr);
        String groupStr = "{$group:{_id:{year:'$year',month:'$month'}},articles:{$push:{title:'$title',_id:'$_id',createAt:'createAt'}}}}";
        DBObject group = (DBObject) JSON.parse(groupStr);
        List<DBObject> list = new ArrayList<DBObject>();
        list.add(match);
        list.add(sort);
        list.add(project);
        list.add(group);
        AggregationOutput output=mongoTemplate.aggregate()
        Iterable<DBObject> map=output.results();
        for (DBObject dbObject:map){
            Map<String,Object> resultmap= (Map<String, Object>) dbObject.get("_id");
            resultmap.get("_id");
            System.out.println("******    "+resultmap.get("_id"));
        }*/

        /**AggregateIterable<org.bson.Document> output=mongoTemplate.getCollection("article").aggregate(Arrays.asList(
                new BasicDBObject("$match",new BasicDBObject("state",1)),
                new BasicDBObject("$sort",new BasicDBObject("createAt",1)),
                new BasicDBObject("$project",new BasicDBObject("year",new BasicDBObject("$year","'$createAt'"))
                        .append("month",new BasicDBObject("$month","'$createAt'"))
                        .append("title",1)
                        .append("createAt",1)),
                new BasicDBObject("$group",new BasicDBObject("_id",new BasicDBObject("year","'$year'")
                        .append("month","'$month'"))
                        .append("article",new BasicDBObject("$push",new BasicDBObject("title","'$title'")
                                .append("_id","'$_id'")
                                .append("createAt","'$createAt'"))))
        ));
        System.out.println("*****"+output.toString());
        for (org.bson.Document dbobject:output){
            System.out.println(dbobject.toJson());
        }*/

        List<Document> pipeline = new ArrayList<Document>();
        Document match = new Document("$match",new Document("state",1));
        pipeline.add(match);
        Document sort=new Document("$sort",new Document("createAt",1));
        pipeline.add(sort);
        Document project=new Document("$project",new Document("year",new Document("$year","$createAt"))
                .append("month",new Document("$month","$createAt"))
                .append("title",1)
                .append("createAt",1));
        pipeline.add(project);
        Document group=new Document("$group",new Document("_id",new Document("year","$year")
                .append("month","$month"))
                .append("article",new Document("$push",new Document("title","$title")
                        .append("_id","$_id")
                        .append("createAt","$createAt"))));
        pipeline.add(group);
        MongoCursor<Document> cursor=mongoTemplate.getCollection("article").aggregate(pipeline).allowDiskUse(true).iterator();
        while (cursor.hasNext()){
            Document item=cursor.next();
            System.out.println(item.toJson());
        }
        cursor.close();
    }

}
