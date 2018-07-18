<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售情况</title>


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
		$("#main2").show(500);
		$("#main").hide();
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
     	<button class="btn btn-default" title="按商品类型查看" id="show1">销售额</button>
     	<button class="btn btn-default" title="当月各类型商品销量" id="show2">销量</button>
     	<button class="btn btn-default" title="每月总销售额" id="show3">总销售额</button>	
     	
     	<br /><br />
     	<div id="main2" style="width: 38%;height:400px;" class="well"></div>
    <div id="main" style="width: 38%;height:400px;" class="well"></div>
    <div id="main1" style="width: 38%;height:400px;" class="well"></div>
    </div>
    </div>
    <script>

        var s=${analyzeMerchant};
        var str=JSON.stringify(s);
        var result= JSON.parse(str+"");
        //document.write(result[0].type);
        var myChart=echarts.init(document.getElementById('main2'));
        option = {
            legend: {},
            tooltip: {
                trigger: 'axis',
                showContent: false
            },
            dataset: {
                source: [
                    ['product', '6', '7', '8', ],
                    ['文具卡片', result[0].money, result[5].money, result[10].money],
                    ['特色美食', result[1].money, result[6].money, result[11].money],
                    ['服饰箱包', result[2].money, result[7].money, result[12].money],
                    ['居家生活', result[3].money, result[8].money, result[13].money],
                    ['数码电器', result[4].money, result[9].money, result[14].money]
                ]
            },
            xAxis: {type: 'category'},
            yAxis: {gridIndex: 0},
            grid: {top: '55%'},
            series: [
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {
                    type: 'pie',
                    id: 'pie',
                    radius: '30%',
                    center: ['50%', '25%'],
                    label: {
                        formatter: '{b}: {@2012} ({d}%)'
                    },
                    encode: {
                        itemName: 'product',
                        value: '2012',
                        tooltip: '2012'
                    }
                }
            ]
        };

        myChart.on('updateAxisPointer', function (event) {
            var xAxisInfo = event.axesInfo[0];
            if (xAxisInfo) {
                var dimension = xAxisInfo.value + 1;
                myChart.setOption({
                    series: {
                        id: 'pie',
                        label: {
                            formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                        },
                        encode: {
                            value: dimension,
                            tooltip: dimension
                        }
                    }
                });
            }
        });

        myChart.setOption(option);

        var myChart = echarts.init(document.getElementById('main'));
        var option = {
            legend: {},
            tooltip: {},
            dataset: {
                source: [
                    ['product', '6','7','8'],
                    ['文具卡片', result[0].num, result[5].num, result[10].num],
                    ['特色美食', result[1].num, result[6].num, result[11].num],
                    ['服饰箱包', result[2].num, result[7].num, result[12].num],
                    ['居家生活', result[3].num, result[8].num, result[13].num],
                    ['数码电器', result[4].num, result[9].num, result[14].num]

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


        echarts.init(document.getElementById('main1')).setOption({
            title: {
                text: '近三月总销售额'
            },
            tooltip: {},
            legend: {
                data:['总销售额']
            },
            xAxis: {
                data: ["六月","七月","八月"]
            },
            yAxis: {},
            series: [{
                name: '总销售额',
                type: 'bar',
                data: [result[0].totMoney, result[5].totMoney, result[10].totMoney,]
            }]
        });


    </script>
    	
 	</body>

</html>