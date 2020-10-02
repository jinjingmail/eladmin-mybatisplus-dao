package me.zhengjie.modules.system.service;

import me.zhengjie.base.CommonService;
import me.zhengjie.base.PageInfo;
import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.service.dto.UserDto;
import me.zhengjie.modules.system.service.dto.UserQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* @author jinjin
* @date 2020-09-25
*/
public interface UserService  extends CommonService<User>{

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<UserDto>
    */
    PageInfo<UserDto> queryAll(UserQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<UserDto>
    */
    List<UserDto> queryAll(UserQueryParam query);

    User getById(Long id);
    UserDto findById(Long id);

    /**
     * 根据用户名查询
     * @param userName /
     * @return /
     */
    User getByUsername(String userName);
    UserDto findByName(String userName);
    /**
     * 插入一条新数据。
     */
    boolean save(UserDto resources);
    boolean updateById(UserDto resources);
    boolean removeById(Long id);
    boolean removeByIds(Set<Long> ids);

    /**
     * 修改密码
     * @param username 用户名
     * @param encryptPassword 密码
     */
    void updatePass(String username, String encryptPassword);

    /**
     * 修改头像
     * @param file 文件
     * @return /
     */
    Map<String, String> updateAvatar(MultipartFile file);

    /**
     * 修改邮箱
     * @param username 用户名
     * @param email 邮箱
     */
    void updateEmail(String username, String email);

    /**
     * 用户自助修改资料
     * @param resources /
     */
    void updateCenter(User resources);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<UserDto> all, HttpServletResponse response) throws IOException;

}
