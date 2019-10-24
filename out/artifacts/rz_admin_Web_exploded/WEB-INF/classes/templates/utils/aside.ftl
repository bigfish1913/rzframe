<aside>
    <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
            <#--<p class="centered"><a href="profile.html"><img src="img/ui-sam.jpg" class="img-circle" width="80"></a></p>-->

            <#--<h5 class="centered">${userInfo.getUserName()}</h5>-->
            <li class="mt">
                <a class="active" href="/home">
                    <i class="fa fa-dashboard"></i>
                    <span>Dashboard</span>
                </a>
            </li>
            <#list menus as item>
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa ${item.getIcon()}"></i>
                        <span>${item.getMenuName()}</span>
                    </a>
                    <#if item.getSubMeuns()?? && (item.getSubMeuns()?size > 0) >
                        <ul class="sub">
                            <#list item.getSubMeuns() as subItem>
                                <#--<i class="fa ${item.getIcon()}"></i>-->
                                <li><a href="${subItem.getMenuUrl()}">${subItem.getMenuName()}</a></li>
                            </#list>

                        </ul>
                    </#if>
                </li>

            </#list>

        </ul>
        <!-- sidebar menu end-->
    </div>
</aside>