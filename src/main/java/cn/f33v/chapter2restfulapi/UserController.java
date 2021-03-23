package cn.f33v.chapter2restfulapi;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2021/03/23 9:24
 * @Created by Administrator
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
    //创建线程安全map,模拟users信息存储
    static Map<Long,User> userMap= Collections.synchronizedMap(new HashMap<>());

    /**
     * 处理"/users/"的GET请求,用来获取用户列表
     * @return 用户列表
     */
    @GetMapping("/")
    public List<User> getUserList(){
        //可以通过@RequestParam从严中传递参数进行查询条件或翻页信息
        return new ArrayList<>(userMap.values());
    }

    /**
     * 处理"/users/"POST请求,用来创建User
     * @param user 用户
     * @return 信息
     */
    @PostMapping("/")
    public String postUser(@RequestBody User user){
        //@RequestBody注解用来绑定通过http请求中application/json类型上传的数据
        userMap.put(user.getId(),user);
        return "success";
    }

    /**
     * 处理"/users/{id}"的GET请求,用来获取url中id值的User信息
     * @param id id
     * @return user
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        //url中的id可以通过@PathVariable绑定到函数的参数中
        return userMap.get(id);
    }

    /**
     * 处理"/users/{id}"的PUT请求,用来更新User信息
     * @param id id
     * @param user user
     * @return 信息
     */
    @PutMapping("/{id}")
    public String putUser(@PathVariable Long id,@RequestBody User user){
        User user1=userMap.get(id);
        user1.setName(user.getName());
        user1.setAge(user.getAge());
        userMap.put(id,user1);
        return "success";
    }

    /**
     * 处理"/users/{id}"的DELETE请求,用来删除User
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userMap.remove(id);
        return "success";
    }
}
