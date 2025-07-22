<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>     <!-- 这个isELIgnored必须加上！否则不识别EL表达式-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<div id="app">
<h2>${user.username}, welcome!</h2>

<input type="button" value="新增" id="add"><br>

<hr>
<table id="brandTable" border="1" cellspacing="0" width="80%">
    <tr>
        <th>序号</th>
        <th>品牌名称</th>
        <th>企业名称</th>
        <th>排序</th>
        <th>品牌介绍</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <!--
        使用v-for遍历tr
    -->
    <tr v-for="(brand,i) in brands" align="center">
        <td>{{brand.id}}</td>
        <td>{{brand.brandName}}</td>
        <td>{{brand.companyName}}</td>
        <td>{{brand.ordered}}</td>
        <td>{{brand.description}}</td>
        <td>{{brand.statusStr}}</td>
        <td><a :href="'/LoginDemo/selectByIdServlet?id=' + brand.id">修改</a>
            <a href="#">删除</a></td>
    </tr>

</table>

</div>

<script src="js/axios-0.18.0.js"></script>
<script src="js/vue.js"></script>

<script>
    document.getElementById("add").onclick = function(){
        location.href = "/LoginDemo/addBrand.html";

    }

    // //1. 当页面加载完成后，发送ajax请求
    // window.onload = function () {
    //     //2. 发送ajax请求
    //     axios({
    //         method:"get",
    //         url:"http://localhost:8080/LoginDemo/selectAllServlet"
    //     }).then(function (resp) {
    //         //获取数据
    //         let brands = resp.data;
    //         let tableData = " <tr>\n" +
    //             "        <th>序号</th>\n" +
    //             "        <th>品牌名称</th>\n" +
    //             "        <th>企业名称</th>\n" +
    //             "        <th>排序</th>\n" +
    //             "        <th>品牌介绍</th>\n" +
    //             "        <th>状态</th>\n" +
    //             "        <th>操作</th>\n" +
    //             "    </tr>";
    //
    //         for (let i = 0; i < brands.length ; i++) {
    //             let brand = brands[i];
    //
    //             tableData += "\n" +
    //                 "    <tr align=\"center\">\n" +
    //                 "        <td>"+brand.id+"</td>\n" +
    //                 "        <td>"+brand.brandName+"</td>\n" +
    //                 "        <td>"+brand.companyName+"</td>\n" +
    //                 "        <td>"+brand.ordered+"</td>\n" +
    //                 "        <td>"+brand.description+"</td>\n" +
    //                 "        <td>"+brand.status+"</td>\n" +
    //                 "\n" +
    //                 "        <td><a href=\"/LoginDemo/selectByIdServlet?id=" + brand.id + "\">修改</a> <a href=\"#\">删除</a></td>\n" +
    //                 "    </tr>";
    //         }
    //         // 设置表格数据
    //         document.getElementById("brandTable").innerHTML = tableData;
    //     })
    // }

    new Vue({
        el:"#app",
        data(){
            return{
                brands:[]
            }
        },
        mounted(){
            // 页面加载完成后，发送异步请求
            //2. 发送ajax请求
            var _this = this;
                axios({
                    method:"get",
                    url:"http://localhost:8080/LoginDemo/selectAllServlet"
                }).then(function (resp) {
                    _this.brands = resp.data;       // this是windows对象(axios框架决定)，_this是vue对象
                })
        }

    })

</script>

</body>
</html>