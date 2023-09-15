## 利用 Python3.x 实现 JAVA 类名替换功能

#### 原始文件
RequestLogPermissionLinkClientController.java

#### 目标文件
RequestLogPermissionLinkController.java

#### 原始文件代码
```java
package com.xxx.admin.web.apply;

import com.xxx.common.dto.ConditionFilterDTO;
import com.xxx.error.common.Response;
import com.xxx.idm.client.apply.client.RequestLogPermissionLinkClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 下推任务调度表权限变更关联表 前端控制器
 * </p>
 *
 * @author zy
 * @since  2023-06-16
 */
@SuppressWarnings({ "rawtypes" })
@RequestMapping("/apply/permission")
@RestController
@Slf4j
public class RequestLogPermissionLinkClientController {

    @Resource
    private RequestLogPermissionLinkClient requestLogPermissionLinkClient;
    /**
     * <p>
     * 获取任务执行的相关的权限详情列表
     * </p>
     *
     * @param  dto 任务表的请求id,分页条件
     * @return     分页数据
     */
    @PostMapping("/list")
    Response listItem(@RequestBody ConditionFilterDTO dto) {
        return requestLogPermissionLinkClient.listItem(dto);
    }
}
```


#### 目标文件代码
```java
package com.xxx.admin.web.apply;

import com.xxx.common.dto.ConditionFilterDTO;
import com.xxx.error.common.Response;
import com.xxx.idm.client.apply.client.RequestLogPermissionLinkClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 下推任务调度表权限变更关联表 前端控制器
 * </p>
 *
 * @author zy
 * @since  2023-06-16
 */
@SuppressWarnings({ "rawtypes" })
@RequestMapping("/apply/permission")
@RestController
@Slf4j
public class RequestLogPermissionLinkController {

    @Resource
    private RequestLogPermissionLinkClient requestLogPermissionLinkClient;
    /**
     * <p>
     * 获取任务执行的相关的权限详情列表
     * </p>
     *
     * @param  dto 任务表的请求id,分页条件
     * @return     分页数据
     */
    @PostMapping("/list")
    Response listItem(@RequestBody ConditionFilterDTO dto) {
        return requestLogPermissionLinkClient.listItem(dto);
    }
}
```

#### 核心代码
```python
import os
#存储文件名map， key：老文件全路径名，value：修改后文件名全路径
nameMap = {}
def findAllFile(base):
    for root, ds, fs in os.walk(base):
        for f in fs:
            nameMap[root + '/' + f] = str(root + '/' + f).replace('ClientController', 'Controller')
            yield f
def operate(path):
    # Use a breakpoint in the code line below to debug your script.
    print(f'path: {path}')  # Press ⌘F8 to toggle the breakpoint.

    for i in findAllFile(path):
        print(i)

    print('>>>>>>>>>>')
    print(nameMap)

    # 存储原始类文件map，key：新文件全路径名， value：老文件真实内容
    classMap = {}
    for key in nameMap.keys():
        f = open(key, 'r')
        # lines = f.readlines()
        classMap[nameMap[key]] = f.readlines()
        f.close()

    #删除 nameMap 中存在 Client 标记的java类
    # for key in nameMap.keys():
    #     if (str(key).find('ClientController') != -1):
    #         os.remove(key)

    # 文件改名
    for key in nameMap.keys():
        if (str(key).find('ClientController') != -1):
            os.rename(key, nameMap[key])

    #改写文件内容数据
    for classKey in classMap.keys():
        f = open(classKey, 'w')
        lines = classMap[classKey]
        for line in lines:
            if (line.find('public class') != -1):
                f.writelines(line.replace('Client', ''))
            else:
                f.writelines(line)
        f.close()


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    path = '/Users/dearzhang/paraview/code/iam/admin/admin-web/src/main/java/com/paraview/admin/web/apply/'
    operate(path)

```