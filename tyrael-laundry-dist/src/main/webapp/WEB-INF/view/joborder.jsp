<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html>

<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>Tyrael Laundry</title>
<link rel="icon" type="image/x-icon" href="/images/favicon.ico" />
<link rel="stylesheet" href="<@spring.url '/lib/bootstrap/dist/css/bootstrap.min.css' />" />
<link rel="stylesheet" href="<@spring.url '/lib/font-awesome/css/font-awesome.min.css' />" />
<link rel="stylesheet" href="<@spring.url '/lib/app.css' />" />
<style>
body {
  font-size: 1.2em;
}
</style>
</head>

<body>

<#if jobOrder??>
<div class="col-sm-4">
<div  class="text-center" class="row">
  <div id="barcode" style="margin: auto;">${jobOrder.trackingNo }</div>
</div>
<table style="width: 100%;">
  <tr>
    <td class="text-right" width="50%;"><strong>Tracking Number</strong></td>
    <td style="padding-left: 10px;">${jobOrder.trackingNo }</td>
  </tr>
  <#if regCode??>
  <tr>
    <td class="text-right"><strong>Registration Code</strong></td>
    <td style="padding-left: 10px;">${regCode.registrationCode }</td>
  </tr>
  </#if>
  <tr>
    <td class="text-right"><strong>Customer</strong></td>
    <#if jobOrder.customer??><td style="padding-left: 10px;">${jobOrder.customer.formattedName}</td>
    <#else><td style="padding-left: 10px;">Name not shown</td>
    </#if>
  </tr>
  <tr>
    <td class="text-right"><strong>Address</strong></td>
    <#if jobOrder.customer??><td style="padding-left: 10px;">${jobOrder.customer.formattedAddress }</td>
    <#else><td style="padding-left: 10px;">Address not shown</td>
    </#if>
  </tr>
  <tr>
    <td class="text-right"><strong>Date Received</strong></td>
    <td style="padding-left: 10px;">${jobOrder.dateReceived.toString('MMM-dd-yyyy')}</td>
  </tr>
  <tr>
    <td class="text-right"><strong>Date Due</strong></td>
    <td style="padding-left: 10px;">${jobOrder.dateDue.toString('MMM-dd-yyyy')}</td>
  </tr>
  <tr>
    <td class="text-right"><strong>Total Amount</strong></td>
    <td style="padding-left: 10px;">Php ${jobOrder.totalAmount?string(",##0.00")}</td>
  </tr>
  <tr>
    <td class="text-right"><strong>Status</strong></td>
    <td style="padding-left: 10px;">${jobOrder.status }</td>
  </tr>
</table>
<!-- 
<#list jobOrder.jobServices as service>
<div class="mt5 servicepanel-small">
  <div class="panel panel-info">
    <div class="panel-heading">
      <div class="row">
        <div class="col-sm-3">
          <img src="/images/icons/${service.serviceType.icon}" class="img-thirtytwo" />
        </div>
        <div class="col-sm-9 text-right">
          <div class="large">${service.serviceType.label}</div>
          <div><strong>${service.weightInKilos}</strong> Kg @ Php ${service.pricePerKilo}/Kg</div>
        </div>
      </div>
    </div>
    <div class="panel-footer">
      <span class="pull-left">Php ${(service.weightInKilos * service.pricePerKilo)?string(",##0.00")}</span> 
      <div class="clearfix"></div>
    </div>
  </div>
</div>
</#list>
 -->
</#if>

<#if !jobOrder??>
  <h1>Job Order not found!</h1>
</#if>

</body>

<script type="text/javascript" src="<@spring.url '/lib/jquery/dist/jquery.min.js' />"></script>
<script type="text/javascript" src="<@spring.url '/lib/jquery-barcode/jquery-barcode.min.js' />"></script>
<script type="text/javascript">
$(function(){
  var $barcode = $('#barcode');
  var barcode = $barcode.html();
  $("#barcode").barcode(barcode, "ean13", {barWidth: 2, barHeight: 80});
});
</script>