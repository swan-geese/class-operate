
# 实现一个遍历指定文件夹下如果存在 .java 文件，
# 判断该java文件中是否包含 Client 字符串，如果包含则剔除该字符，同理java类中类名如果包含 Client 字符串，
# 也一并处理，例如存在一个 AppClientController.java 类，则处理为 AppController.java

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
    path = '/Users/dearzhang/paraview/code/iam/admin/admin-web/src/main/java/com/paraview/admin/web/'
    operate(path)


