<!DOCTYPE html>
<html lang="en">
<header>
    <meta charset="UTF-8">
    <title>RZ</title>
    <link href="/css/demo_page.css" rel="stylesheet">
    <link href="/css/demo_table.css" rel="stylesheet">
    <#--<link href="/css/jquery.dataTables.css" rel="stylesheet">-->
    <link href="/css/DT_bootstrap.css" rel="stylesheet">
    <#include "utils/cssheader.ftl"/>



</header>
<body>

<section id="container">

    <#include "utils/header.ftl"/>
    <#include "utils/aside.ftl"/>

    <section id="main-content">
        <section class="wrapper">
            <h3>用户管理</h3>

            <table class="table table-bordered table-striped table-condensed" id="usertable">

            </table>
        </section>
    </section>


    <#include "utils/footer.ftl"/>
</section>
</body>
</html>
<#include "utils/script.ftl"/>
<script src="/js/libs/jquery.dataTables.min.js"></script>
<script src="/js/user.js"></script>
