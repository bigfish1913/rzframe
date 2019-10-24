var user = function () {
    var _init = function () {
        var data = [['', '三体', '刘慈欣', '39.00', '重庆出版社', '982513213516']]
        var titles = ['书名', '作者', '价格', '出版社', 'ISBN']
        $('#usertable').DataTable({
            "processing": true,
            "searching": true,
            "serverSide": true,
            ordering: false,
            "ajax": {
                "url": "/user/getAllUser",
            },
            "columns": [
                {"data": "loginName", title: "登录名"},
                {"data": "userName", title: "姓名"},
                {"data": "userId", title: "用户编号"},
                {"data": "userMobileno", title: "手机号"},
                {"data": "userEmail", title: "邮箱"},
                {"data": "userStatus", title: "状态"},
                {"data": "createTime", title: "注册时间"}
            ],
            "pagingType": "full_numbers",
            // "bSort": true,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
            // "columnDefs": [{
            //     "searchable": false,
            //     "orderable": true,
            //     "targets": 0
            // }],
            // "order": [[1, 'asc']]

        });
    }

    return {
        init: _init
    }
}();
user.init();