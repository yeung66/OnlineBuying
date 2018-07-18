<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>消费情况</title>


    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <script src="../js/bootstrap.js"></script>
    <script src="../js/jquery.easing.1.3.js"></script>
    <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/echarts.js"></script>
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <script src="../js/jquery.min.js"></script>

    <script>
        $(document).ready(function() {
            $("#main").show(500);
            $("#main2").hide();
            $("#main1").hide();
            $("#show1").click(function(){
                $("#main2").show(500);
                $("#main").hide();
                $("#main1").hide();

            });
            $("#show2").click(function(){
                $("#main").show(500);
                $("#main2").hide();
                $("#main1").hide();

            });
            $("#show3").click(function(){
                $("#main1").show(500);
                $("#main").hide();
                $("#main2").hide();

            })
        });
    </script>
</head>

<body>
<jsp:include page="head.jsp" />
<div align="center">
    <br /><br /><br />
    <div class="panel-footer" align="center" style="width: 80%;height: 800px;">

        <button class="btn btn-default" title="近三月各类型花销详情" id="show2">花销详情</button>
        <button class="btn btn-default" title="当月花销占比" id="show3">花销占比</button>
        <button class="btn btn-default" title="近三月花销总额" id="show1">花销总额</button>

        <br /><br />
        <div id="main2" style="width: 38%;height:400px;" class="well"></div>
        <div id="main" style="width: 38%;height:400px;" class="well"></div>
        <div id="main1" style="width: 38%;height:400px;" class="well"></div>
    </div>
</div>
<script type="text/javascript">
    //买家
    var s=${analyzeCustomer};
    var str=JSON.stringify(s);
    var result= JSON.parse(str+"");
    var myChart = echarts.init(document.getElementById('main'));
    var option = {

        legend: {},
        tooltip: {},
        dataset: {
            source: [
                ['product', '6','7','8'],
                ['文具卡片', result[0].money, result[5].money, result[10].money],
                ['特色美食', result[1].money, result[6].money, result[11].money],
                ['服饰箱包', result[2].money, result[7].money, result[12].money],
                ['居家生活', result[3].money, result[8].money, result[13].money],
                ['数码电器', result[4].money, result[9].money, result[14].money]
            ]
        },
        xAxis: [
            {type: 'category', gridIndex: 0},

        ],
        yAxis: [
            {gridIndex: 0},

        ],
        grid: [

            {top: '15%'}
        ],
        series: [
            // These series are in the first grid.
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            // These series are in the second grid.



        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    //第二张
    echarts.init(document.getElementById('main1')).setOption({
        title: {
            text: '当月花销占比',
            left: 'center',
            textStyle: {
                color: '#ccc'
            }
        },
        backgroundColor: '#2c343c',
        visualMap: {
            show: false,
            min: 1,
            max: 1000,
            inRange: {
                colorLightness: [0, 1]
            }
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {d}%"
        },
        series : [
            {
                name: '开销占比',
                type: 'pie',
                radius: '55%',

                data:[
                    {value:result[10].money, name:'文具卡片',itemStyle:{normal:{color:'#c23531'}}},
                    {value:result[11].money, name:'特色美食',itemStyle:{normal:{color:'#c23531'}}},
                    {value:result[12].money, name:'服饰箱包',itemStyle:{normal:{color:'#c23531'}}},
                    {value:result[13].money, name:'居家生活',itemStyle:{normal:{color:'#c23531'}}},
                    {value:result[14].money, name:'数码电器',itemStyle:{normal:{color:'#c23531'}}}
                ],
                roseType: 'angle',
                label: {
                    normal: {
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        lineStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#c23531',
                        shadowBlur: 200,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    });

</script>
<script>
    var myChart=echarts.init(document.getElementById('main2'));
    option = {
        title: {
            text: '近三月总开销'
        },
        tooltip: {},
        legend: {
            data:['开销']
        },
        xAxis: {
            data: ['六月', '七月', '八月']
        },
        yAxis: {},
        series: [{
            name: '开销',
            type: 'bar',
            data: [result[0].totMoney, result[5].totMoney, result[10].totMoney]
        }]
    };



    myChart.setOption(option);
</script>

</body>

</html>