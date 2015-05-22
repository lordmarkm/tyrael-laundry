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
td {
  padding: 1px;
}
</style>
</head>

<body>

<#if queue??>
<table border="1">
  <thead>
    <tr>
      <th>Type
      <th>Details
      <th>Address
      <th>Status
    </tr>
  </thead>
  <tbody>
  <#list queue.deliveryRequests as delivery>
  <tr>
    <td>Delivery
    <td>${delivery.jobOrder.trackingNo } - ${delivery.customer.formattedName }
    <td>${delivery.address.formattedAddress }
    <td>
      <input type="checkbox" disabled>OK
      <input type="checkbox" disabled>NA
      <input type="checkbox" disabled>INV
      <input type="checkbox" disabled>NF
    </td>
  </tr>
  </#list>
  <#list queue.pickupRequests as pickup>
  <tr>
    <td>Pickup
    <td>${pickup.customer.formattedName }
    <td>${pickup.address.formattedAddress }
    <td>
      <input type="checkbox" disabled>OK
      <input type="checkbox" disabled>NA
      <input type="checkbox" disabled>INV
      <input type="checkbox" disabled>NF
    </td>
  </tr>
  </#list>
  </tbody>
</table>
<strong>NA</strong>-No Answer <strong>INV</strong>-Address Invalid <strong>NF</strong>-Address not found
</#if>

<#if !queue??>
  <h1>Job Order not found!</h1>
</#if>

</body>

<script type="text/javascript" src="<@spring.url '/lib/jquery/dist/jquery.min.js' />"></script>