/*****
 * 定义一个Specification控制层 controller
 * 发送HTTP请求和后台进行数据交互
 ****/
var app=new Vue({
    el:'#app',
    data:{
        dataList:[],        //集合数据
        pageNo: 1,          //当前页
        pages:15,           //总页数
        searchEntity:{},   //搜索对象
        entity:{specificationOptionList:[]},          //增加的数据封装
        ids:[]              //复选框选中的ID
    },
    methods:{
        //移除一条记录
        removeTableRow:function (index) {
            //移除集合中对应下标的数据
            app.entity.specificationOptionList.splice(index,1);
        },


        //点击添加的时候，添加一个规格选项记录
        addTableRow:function () {
            //js往集合中添加一条记录，使用push
            app.entity.specificationOptionList.push({});
        },


        //搜索方法
        searchList:function (curPage) {
            axios.post('/specification/list.shtml?pageNum='+curPage,this.searchEntity).then(function (response) {
                //获取数据
                app.dataList=response.data.list;

                //当前页
                app.pageNo=curPage;
                app.pages=response.data.pages;
            });
        },

        //保存
        save:function () {
            //增加操作
            if(app.entity.id==null){
                app.add();
            }else{
                //修改操作
                app.updateById();
            }
        },

        //增加Specification
        add:function () {
            axios.post('/specification/add.shtml',this.entity).then(function (response) {
                if(response.data.success){
                    //清空数据
                    app.entity={specificationOptionList:[]};

                    //页面刷新
                    app.searchList(1);

		    //页码设置成第1页
                    app.$children[0].goPage(1);
                }else{
                    alert(response.message);
                }
            });
        },

        //修改
        updateById:function () {
            axios.post('/specification/modify.shtml',this.entity).then(function (response) {
                if(response.data.success){
                    //清空原有数据
                    app.entity={specificationOptionList:[]};
                    //刷新页面
                    app.searchList(1);

		    //页码设置成第1页
                    app.$children[0].goPage(1);
                }
            });
        },

        //根据ID查询
        getById:function (id) {
            axios.get('/specification/one/'+id+'.shtml').then(function (response) {
                app.entity=response.data;
            })
        },

        //删除方法
        deleteSpecification:function () {
            axios.post('/specification/delete.shtml',this.ids).then(function (response) {
                if(response.data.success){
                    //删除成功，刷新页面
                    app.searchList(1);

                    //清空选择的数据
                    app.ids=[];

		            //页码设置成第1页
                    app.$children[0].goPage(1);
                }else{
                    alert(response.data.message);
                }
            });
        }
    },
    created:function () {
        this.searchList(1);
    }
});
