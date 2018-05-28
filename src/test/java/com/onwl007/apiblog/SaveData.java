package com.onwl007.apiblog;

import com.onwl007.apiblog.domain.*;
import com.onwl007.apiblog.repository.*;
import com.onwl007.apiblog.vo.ArticleMeta;
import com.onwl007.apiblog.vo.CommentMeta;
import com.onwl007.apiblog.vo.Github;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiBlogApplication.class)
public class SaveData {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    MomentRepository momentRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testSaveArticle(){
        Article article=new Article();
        article.setCategory(categoryRepository.findCategoryByName("code"));
        article.setContent("> 阅读 《深入理解 Java 虚拟机》一书的读书笔记\n" +
                "\n" +
                "**Java 内存区域与溢出异常**\n" +
                "\n" +
                "![JVM 运行时数据区域](https://raw.githubusercontent.com/onwl007/Markdown-Photos/master/JVM%20%E5%86%85%E5%AD%98%E5%8C%BA%E5%9F%9F.png)\n" +
                "\n" +
                "Java 虚拟机在执行 Java 程序的过程中会把它所管理的内存区域划分为若干个不同的数据区域\n" +
                "![JVM 运行时数据区](http://upload-images.jianshu.io/upload_images/1417629-3a2a7c7286d0418a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)\n" +
                "\n" +
                "\n" +
                "**程序计数器**\n" +
                "是一块较小的内存空间，主要用来指示执行哪条指令。可以看作是当前线程所执行的字节码的行号指示器，字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令。\n" +
                "\n" +
                "每一个线程都有一个自己的程序计数器，是线程私有的内存。如果正在执行的是一个 Java 方法，这个计数器记录的是正在执行的虚拟机字节码指令的地址，如果正在执行的是 Native 方法，这个计数器则为空（Undefined）。此内存区域是唯一一个在 Java 虚拟机规范中没有规定任何 OutOfMemoryError 情况的区域。\n" +
                "\n" +
                "**Java 虚拟机栈**\n" +
                "它描述的是 Java 方法执行的内存模型，也就是说一个 Java 方法的执行，会在虚拟栈中存储局部变量表，操作数栈，动态链接，出口信息等，每一个方法从开始执行到结束，对应的就是从入栈到出栈的过程。\n" +
                "\n" +
                "局部变量表存储了基本的数据类型，如 boolean，int，long 等，对象引用类型。其中 64 位长度的 long 和 double 类型的数据会占用两个局部变量表空间，其余的数据类型只占用一个。局部变量表所需的内存空间在编译期间完成分配，当进入一个方法时，这个方法需要在栈中分配多大的局部变量表空间是完全确定的，在方法期间不会改变局部变量表的大小。\n" +
                "\n" +
                "其中有两种内存溢出的异常，如果线程请求的栈深度大于虚拟机所允许的深度，将抛出 StackOverflowError 异常；如果扩展时无法申请到足够的内存，则抛出 OutOfMemoryError 异常。\n" +
                "\n" +
                "**本地方法栈**\n" +
                "顾名思义，本地方法栈就是为 Native 方法服务的，与虚拟机栈所发挥的作用是非常相似的，本地方法栈也会抛出 StackOverflowError 和 OutOfMemoryError 两种异常。\n" +
                "\n" +
                "**Java 堆**\n" +
                "这一块是 Java 虚拟机中占用内存最大的一块，是被所有线程共享的一块内存区域，此内存区域的唯一目的就是存放对象实例。Java 堆也是垃圾收集器管理的主要区域，因此也被成为 “GC 堆”。Java 堆还可以细分为新生代和老年代，线程共享的 Java 堆中可能划分出多个线程私有的分配缓冲区。\n" +
                "\n" +
                "Java 堆可以处于物理上不连续的内存空间中，只要逻辑上是连续的即可。\n" +
                "\n" +
                "**方法区**\n" +
                "也是各个线程共享的内存区域，用于存储已被虚拟机加载的类信息，常量，静态变量，即时编译期编译后的代码等数据。Java 虚拟机规范把方法区描述为堆的一个逻辑部分。这个内存区域回收的主要目标是针对常量池的回收和对类型的卸载。\n" +
                "\n" +
                "**运行时常量池**\n" +
                "是方法区的一部分，用于存放编译期生成的各种字面量和符号引用，当常量池无法再申请到内存时会抛出 OutOfMemoryError 异常。\n" +
                "\n" +
                "**直接内存**\n" +
                "并不是虚拟机运行时数据区的一部分，也不是 Java 虚拟机规范中定义的内存区域。\n" +
                "\n" +
                "在 JDK 1.4 中新加入了NIO（New Input/Output）类，引入了一种基于通道与缓冲区的 I/O 方式，可以使用 Native 函数库直接分配堆外内存，然后通过一个存储在 Java 堆中的 DirectByteBuffer 对象作为这块内存的引用进行操作，这样在一些场景中显著提高性能，因此避免了在 Java 堆和 Native 堆中来回复制数据。\n" +
                "\n" +
                "直接内存会受到本机总内存大小以及处理器寻址空间的限制，当各个内存区域总和大于物理内存限制，从而导致动态扩展时出现 OutOfMemoryError 异常。\n" +
                "\n" +
                "> 参考资料：《深入理解 Java 虚拟机》");
        article.setCreatedAt(new Date());
        article.setUpdateAt(new Date());
        article.setDescription("内存模型");
        article.setKeywords("内存");
        ArticleMeta meta=new ArticleMeta();
        meta.setComments(3);
        meta.setUps(2);
        meta.setPvs(12);
        article.setMeta(meta);
        article.setPermalink("https://jooger.me/article/5b081d9880194752234103bb");
        article.setPublishAt(new Date());
        article.setRenderedContent("<blockquote>\n" +
                "<p>阅读 《深入理解 Java 虚拟机》一书的读书笔记</p>\n" +
                "</blockquote>\n" +
                "<p><strong>Java 内存区域与溢出异常</strong></p>\n" +
                "<p> <img class=\"image-view\" src=\"https://raw.githubusercontent.com/onwl007/Markdown-Photos/master/JVM%20%E5%86%85%E5%AD%98%E5%8C%BA%E5%9F%9F.png\" title=\"JVM 运行时数据区域\" alt=\"JVM 运行时数据区域\" > </p>\n" +
                "<p>Java 虚拟机在执行 Java 程序的过程中会把它所管理的内存区域划分为若干个不同的数据区域<br> <img class=\"image-view\" src=\"http://upload-images.jianshu.io/upload_images/1417629-3a2a7c7286d0418a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240\" title=\"JVM 运行时数据区\" alt=\"JVM 运行时数据区\" > </p>\n" +
                "<p><strong>程序计数器</strong><br>是一块较小的内存空间，主要用来指示执行哪条指令。可以看作是当前线程所执行的字节码的行号指示器，字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令。</p>\n" +
                "<p>每一个线程都有一个自己的程序计数器，是线程私有的内存。如果正在执行的是一个 Java 方法，这个计数器记录的是正在执行的虚拟机字节码指令的地址，如果正在执行的是 Native 方法，这个计数器则为空（Undefined）。此内存区域是唯一一个在 Java 虚拟机规范中没有规定任何 OutOfMemoryError 情况的区域。</p>\n" +
                "<p><strong>Java 虚拟机栈</strong><br>它描述的是 Java 方法执行的内存模型，也就是说一个 Java 方法的执行，会在虚拟栈中存储局部变量表，操作数栈，动态链接，出口信息等，每一个方法从开始执行到结束，对应的就是从入栈到出栈的过程。</p>\n" +
                "<p>局部变量表存储了基本的数据类型，如 boolean，int，long 等，对象引用类型。其中 64 位长度的 long 和 double 类型的数据会占用两个局部变量表空间，其余的数据类型只占用一个。局部变量表所需的内存空间在编译期间完成分配，当进入一个方法时，这个方法需要在栈中分配多大的局部变量表空间是完全确定的，在方法期间不会改变局部变量表的大小。</p>\n" +
                "<p>其中有两种内存溢出的异常，如果线程请求的栈深度大于虚拟机所允许的深度，将抛出 StackOverflowError 异常；如果扩展时无法申请到足够的内存，则抛出 OutOfMemoryError 异常。</p>\n" +
                "<p><strong>本地方法栈</strong><br>顾名思义，本地方法栈就是为 Native 方法服务的，与虚拟机栈所发挥的作用是非常相似的，本地方法栈也会抛出 StackOverflowError 和 OutOfMemoryError 两种异常。</p>\n" +
                "<p><strong>Java 堆</strong><br>这一块是 Java 虚拟机中占用内存最大的一块，是被所有线程共享的一块内存区域，此内存区域的唯一目的就是存放对象实例。Java 堆也是垃圾收集器管理的主要区域，因此也被成为 “GC 堆”。Java 堆还可以细分为新生代和老年代，线程共享的 Java 堆中可能划分出多个线程私有的分配缓冲区。</p>\n" +
                "<p>Java 堆可以处于物理上不连续的内存空间中，只要逻辑上是连续的即可。</p>\n" +
                "<p><strong>方法区</strong><br>也是各个线程共享的内存区域，用于存储已被虚拟机加载的类信息，常量，静态变量，即时编译期编译后的代码等数据。Java 虚拟机规范把方法区描述为堆的一个逻辑部分。这个内存区域回收的主要目标是针对常量池的回收和对类型的卸载。</p>\n" +
                "<p><strong>运行时常量池</strong><br>是方法区的一部分，用于存放编译期生成的各种字面量和符号引用，当常量池无法再申请到内存时会抛出 OutOfMemoryError 异常。</p>\n" +
                "<p><strong>直接内存</strong><br>并不是虚拟机运行时数据区的一部分，也不是 Java 虚拟机规范中定义的内存区域。</p>\n" +
                "<p>在 JDK 1.4 中新加入了NIO（New Input/Output）类，引入了一种基于通道与缓冲区的 I/O 方式，可以使用 Native 函数库直接分配堆外内存，然后通过一个存储在 Java 堆中的 DirectByteBuffer 对象作为这块内存的引用进行操作，这样在一些场景中显著提高性能，因此避免了在 Java 堆和 Native 堆中来回复制数据。</p>\n" +
                "<p>直接内存会受到本机总内存大小以及处理器寻址空间的限制，当各个内存区域总和大于物理内存限制，从而导致动态扩展时出现 OutOfMemoryError 异常。</p>\n" +
                "<blockquote>\n" +
                "<p>参考资料：《深入理解 Java 虚拟机》</p>\n" +
                "</blockquote>\n");
        article.setState(1);
        article.setTag(tagRepository.findTagByName("JVM"));
        article.setThumb("https://static.jooger.me/img/source/20180525/test_1527339217217.jpeg");
        article.setTitle("【深入理解 JVM】之内存区域");
        article.setUpdateAt(new Date());
        articleRepository.save(article);
    }

    @Test
    public void testCategory(){
        Category category=new Category();
        category.setName("code");
        category.setList(1);
        category.setCreateAt(new Date());
        category.setUpdateAt(new Date());
        category.setDescription("代码");
        categoryRepository.save(category);
    }

    @Test
    public void testSaveTag(){
        Tag tag=new Tag();
        tag.setName("JVM");
        tag.setCreateAt(new Date());
        tag.setUpdateAt(new Date());
        tag.setDescription("虚拟机");

        Tag tag1=new Tag();
        tag1.setName("Java");
        tag1.setCreateAt(new Date());
        tag1.setUpdateAt(new Date());
        tag1.setDescription("Java");
        tagRepository.save(tag);
        tagRepository.save(tag1);
    }

    @Test
    public void testSaveComment(){
        Comment comment=new Comment();
        comment.setContent("王先森你真帅 \uD83D\uDE0E \uD83D\uDE0E \uD83D\uDE0E");
        Article article=articleRepository.findByTitle("【深入理解 JVM】之内存区域");
        User user=userRepository.findUserByName("Jooger");
        comment.setArticle(article);
        comment.setAuthor(user);
        comment.setRenderedContent("<p>王先森你真帅 \uD83D\uDE0E \uD83D\uDE0E \uD83D\uDE0E</p>\n");
        CommentMeta meta=new CommentMeta();
        meta.setIp("172.17.0.1");
        meta.setUa("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
        meta.setReferer("http://localhost:3000/article/5b081d9880194752234103bb");
        comment.setMeta(meta);
        comment.setType(0);
        comment.setSticky(0);
        comment.setUps(0);
        comment.setSpam(false);
        comment.setState(1);
        comment.setUpdateAt(new Date());
        comment.setCreateAt(new Date());
        commentRepository.save(comment);

        Comment comment1=new Comment();
        comment1.setContent("一般帅吧");
        comment1.setArticle(article);
        comment1.setAuthor(user);
        comment1.setParent(comment);
        comment1.setForward(comment);
        comment1.setRenderedContent("<p>一般帅吧</p>\n");
        CommentMeta meta1=new CommentMeta();
        meta1.setIp("172.17.0.1");
        meta1.setUa("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
        meta1.setReferer("http://localhost:3000/article/5b081d9880194752234103bb");
        comment1.setMeta(meta1);
        comment1.setType(0);
        comment1.setSticky(0);
        comment1.setUps(0);
        comment1.setSpam(false);
        comment1.setState(1);
        comment1.setUpdateAt(new Date());
        comment1.setCreateAt(new Date());
        commentRepository.save(comment1);

        Comment comment2=new Comment();
        comment2.setContent("皮");
        comment2.setArticle(article);
        comment2.setAuthor(user);
        comment2.setParent(comment);
        comment2.setForward(comment);
        comment2.setRenderedContent("<p>皮</p>\n");
        CommentMeta meta2=new CommentMeta();
        meta2.setIp("172.17.0.1");
        meta2.setUa("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
        meta2.setReferer("http://localhost:3000/article/5b081d9880194752234103bb");
        comment2.setMeta(meta2);
        comment2.setType(0);
        comment2.setSticky(0);
        comment2.setUps(0);
        comment2.setSpam(false);
        comment2.setState(1);
        comment2.setUpdateAt(new Date());
        comment2.setCreateAt(new Date());
        commentRepository.save(comment2);
    }

    @Test
    public void testSaveUser(){
        User user=new User();
        user.setEmail("iamjooger@gmail.com");
        user.setPassword("$2a$08$yOcd8s0G5ixkinHmLyE65OjSeXt47rgD/7wlXAdgTaPQgjQIAMuxu");
        user.setSlogan("It's so far");
        user.setSite("https://jooger.me");
        user.setAvatar("https://avatars1.githubusercontent.com/u/16385416?v=4");
        Github github=new Github();
        github.setLogin("jo0ger");
        github.setId("16385416");
        user.setGithub(github);
        user.setUpdateAt(new Date());
        user.setCreateAt(new Date());
        user.setLocation("Beijing, China");
        user.setCompany("MeiTuan Hotel");
        user.setMute(false);
        user.setRole(0);
        user.setDescription("我最帅");
        user.setName("Jooger");
        userRepository.save(user);
    }
}
