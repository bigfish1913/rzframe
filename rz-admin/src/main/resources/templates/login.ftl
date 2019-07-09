<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>

    <link href="img/favicon.png" rel="icon">
    <link href="img/apple-touch-icon.png" rel="apple-touch-icon">
    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--external css-->
    <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/style-responsive.css" rel="stylesheet">

</head>

<body>
<!-- **********************************************************************************************************************************************************
    MAIN CONTENT
    *********************************************************************************************************************************************************** -->
<div id="login-page">
    <div class="container">
        <form class="form-login" action="valide" style="max-width: 450px" method="post">
            <h2 class="form-login-heading">登录</h2>
            <div class="login-wrap">
                <input type="text" class="form-control" placeholder="用户名" name="userName" autofocus>
                <br>
                <input type="password" class="form-control" placeholder="密码" name="password">
                <label class="checkbox">
                    <input type="checkbox" value="remember-me"> 记住密码
                    <span class="pull-right">
            <a data-toggle="modal" href="login.html#myModal"> 忘记密码?</a>
            </span>
                </label>
                <button class="btn btn-theme btn-block" type="submit"><i class="fa fa-lock"></i> 登录</button>
                <#if errorMsg??>
                <div class="label" style="color: red"> 用户名或密码错误</div>
                </#if>
                <hr>
                <div class="login-social-link centered">
                    <p>其他登录方式</p>
                    <button class="btn btn-facebook" type="submit"><i class="fa fa-wechat"></i> 微信</button>
                    <button class="btn btn-twitter" type="submit"><i class="fa fa-qq"></i> QQ</button>
                    <button class="btn btn-twitter" type="submit"><i class="fa fa-weibo"></i> 微博</button>
                    <button class="btn btn-twitter" type="submit"><i class="fa fa-tencent-weibo"></i> 腾讯微博</button>
                </div>
                <#--<div class="registration">-->
                  <#--注册?<br/>-->
                    <#--<a class="" href="#">-->
                        <#--Create an account-->
                    <#--</a>-->
                <#--</div>-->
            </div>
            <!-- Modal -->
            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Forgot Password ?</h4>
                        </div>
                        <div class="modal-body">
                            <p>Enter your e-mail address below to reset your password.</p>
                            <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">
                        </div>
                        <div class="modal-footer">
                            <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                            <button class="btn btn-theme" type="button">登录</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- modal -->
        </form>
    </div>
</div>
<!-- js placed at the end of the document so the pages load faster -->
<script src="lib/jquery/jquery.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<!--BACKSTRETCH-->
<!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
<script type="text/javascript" src="lib/jquery.backstretch.min.js"></script>
<script>
    $.backstretch("img/login-bg.jpg", {
        speed: 500
    });
</script>
</body>

</html>
