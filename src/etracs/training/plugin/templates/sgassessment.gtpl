<%
    def df = new java.text.DecimalFormat("#,##0.00")
%>

<table width="380" cellpadding="0" >
    <tr style="color:red">
        <td><font size="5"><b>Total Amount</b></font></td>
        <td>:</td>
        <td align="right"><font size="6"><b>${df.format(entity.amountdue)}</b></font></td>
    </tr>
</table>
<br>
<hr>
<br>
<table width="400">
    <tr>
        <td>Payer</td>
        <td>${entity.paidby}</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>${entity.paidbyaddress}</td>
    </tr>
    <tr>
        <td colspan="2">
            <hr>
            <table width="100%">
                <tr>
                    <th>Title</th>
                    <th>Unit Value</th>
                    <th>Amount</th>
                    <th>Remarks</th>
                </tr>
                <%entity.items.each{ %>
                    <tr>                       
                        <td>${it?.item_title}</td>
                        <td>${df.format(it?.defaultvalue)}</td>
                        <td>${df.format(it?.amount)}</td>                        
                        <td>${  (it?.remarks) ? it.remarks : ''}</td>
                    </tr>
                <%}%>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <hr>
                <b>AMOUNT : ${df.format(entity.amountdue)}<BR></b>
           </hr>
        </td>
    </tr>
</table>

