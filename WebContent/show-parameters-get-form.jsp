<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
	<TITLE>A Sample Form using GET</TITLE>
	<style>
            .error { color: red; }
        </style>

	</HEAD>
<BODY BGCOLOR="#FDF5E6">
<H1 ALIGN="CENTER">A Sample Form using GET</H1>
<span class="error"> ${messages.epy}</span><br>
<FORM ACTION="show-params">
  Item Number: <INPUT TYPE="TEXT" NAME="itemNum"><span class="error"> ${messages.itemNum}</span><BR>
  Description: <INPUT TYPE="TEXT" NAME="description"><BR>
  Price Each: <INPUT TYPE="TEXT" NAME="price" VALUE="$"><span class="error"> ${messages.price}</span><BR>
  <HR>
  First Name: <INPUT TYPE="TEXT" NAME="firstName"><span class="error"> ${messages.firstName}</span><BR>
  Last Name: <INPUT TYPE="TEXT" NAME="lastName"><span class="error"> ${messages.lastName}</span><BR>
  Middle Initial: <INPUT TYPE="TEXT" NAME="initial"><span class="error"> ${messages.initial}</span><BR>
  Shipping Address:<INPUT TYPE="TEXT" NAME="address"><span class="error"> ${messages.address}</span><BR>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  City:<INPUT TYPE="TEXT" NAME="city"><span class="error"> ${messages.city}</span><BR>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  State:<INPUT TYPE="TEXT" NAME="state"><span class="error"> ${messages.state}</span><BR>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  Zip Code:<INPUT TYPE="TEXT" NAME="zip"><span class="error"> ${messages.zip}</span><BR>
  Credit Card: <span class="error"> ${messages.cardType}</span><BR>
  &nbsp;&nbsp;<INPUT TYPE="RADIO" NAME="cardType"
                     VALUE="Visa">Visa<BR>
  &nbsp;&nbsp;<INPUT TYPE="RADIO" NAME="cardType"
                     VALUE="MasterCard">MasterCard<BR>
  &nbsp;&nbsp;<INPUT TYPE="RADIO" NAME="cardType"
                     VALUE="Amex">American Express<BR>
  &nbsp;&nbsp;<INPUT TYPE="RADIO" NAME="cardType"
                     VALUE="Discover">Discover<BR>
  Credit Card Number: 
  <INPUT TYPE="PASSWORD" NAME="cardNum"><span class="error"> ${messages.cardNum}</span><BR>
  Repeat Credit Card Number:
  <INPUT TYPE="PASSWORD" NAME="cardNum2"><BR><BR>
  <CENTER><INPUT TYPE="SUBMIT" VALUE="Submit Order"></CENTER>
</FORM>
</BODY></HTML>