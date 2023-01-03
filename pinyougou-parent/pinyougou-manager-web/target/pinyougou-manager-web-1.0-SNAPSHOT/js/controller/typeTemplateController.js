//注册全局组件
Vue.component('v-select', VueSelect.VueSelect);
/*****
 * 定义一个TypeTemplate控制层 controller
 * 发送HTTP请求和后台进行数据交互
 ****/
var app=new Vue({
    el:'#app',
    data:{
        dataList:[],        //集合数据
        pageNo: 1,          //当前页
        pages:15,           //总页数
        searchEntity:{},   //搜索对象
        entity:{brandIds:[],specIds:[],customAttributeItems:[]},          //增加的数据封装
        ids:[],              //复选框选中的ID
        brandList:[],    //品牌数据
        specList:[]    //规格数据
    },
    methods:{
        //处理字符串   华为,小米,魅族
        handlerstr:function (jsonstr,key) {
            //将json字符串转成JSON对象
            var jsonArray = JSON.parse(jsonstr);
            //存储拼接的字符
            var jsonliststr = '';
            //循环JSON对象
            for(var i=0;i<jsonArray.length;i++){
                var json = jsonArray[i];
                if(i>0){
                    //将text的值拼接到一起
                    jsonliststr+=",";
                }
                //获取text数据
                jsonliststr+=json[key];
            }
            //返回
            return jsonliststr;
        },


        //添加扩展属性
        addTableRow:function () {
            app.entity.customAttributeItems.push({});
        },

        //删除指定的扩展属性
        removeTableRow:function (index) {
            app.entity.customAttributeItems.splice(index,1);
        },

        //查询品牌信息
        searchBrandList:function () {
            axios.get("/brand/list.shtml").then(function (response) {
                app.brandList=response.data;
            })
        },

        //查询品牌信息
        searchSpecList:function () {
            axios.get("/specification/list.shtml").then(function (response) {
                app.specList=response.data;
            })
        },

        //搜索方法
        searchList:function (curPage) {
            axios.post('/typeTemplate/list.shtml?pageNum='+curPage,this.searchEntity).then(function (response) {
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

        //增加TypeTemplate
        add:function () {
            axios.post('/typeTemplate/add.shtml',this.entity).then(function (response) {
                if(response.data.success){
                    //清空数据
                    app.entity={brandIds:[],specIds:[],customAttributeItems:[]};

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
            axios.post('/typeTemplate/modify.shtml',this.entity).then(function (response) {
                if(response.data.success){
                    //清空原有数据
                    app.entity={brandIds:[],specIds:[],customAttributeItems:[]};
                    //刷新页面
                    app.searchList(1);

		    //页码设置成第1页
                    app.$children[0].goPage(1);
                }
            });
        },

        //根据ID查询
        getById:function (id) {
            axios.get('/typeTemplate/one/'+id+'.shtml').then(function (response) {
                app.entity=response.data;

                //JSON.parse("将字符串转成JSON格式")
                //品牌、规格、扩展属性转成JSON
                app.entity.brandIds=JSON.parse(app.entity.brandIds);
                app.entity.specIds=JSON.parse(app.entity.specIds);
                app.entity.customAttributeItems=JSON.parse(app.entity.customAttributeItems);
            })
        },

        //删除方法
        deleteTypeTemplate:function () {
            axios.post('/typeTemplate/delete.shtml',this.ids).then(function (response) {
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
        //查询模板数据
        this.searchList(1);

        //初始化调用查询品牌信息
        this.searchBrandList();

        //初始化调用查询规格信息
        this.searchSpecList();
    }
});
