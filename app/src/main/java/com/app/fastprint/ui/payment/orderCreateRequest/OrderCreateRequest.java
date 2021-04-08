package com.app.fastprint.ui.payment.orderCreateRequest;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OrderCreateRequest {

	@SerializedName("shipping_lines")
	private List<ShippingLinesItem> shippingLines;

	@SerializedName("set_paid")
	private boolean setPaid;

	@SerializedName("customer_id")
	private String customer_id;

	@SerializedName("shipping")
	private Shipping shipping;

	@SerializedName("payment_method_title")
	private String paymentMethodTitle;

	@SerializedName("line_items")
	private List<LineItemsItem> lineItems;

	@SerializedName("coupon_lines")
	private List<CouponsItem> couponsItems;

	@SerializedName("payment_method")
	private String paymentMethod;

	@SerializedName("billing")
	private Billing billing;

	public List<ShippingLinesItem> getShippingLines(){
		return shippingLines;
	}

	public boolean isSetPaid(){
		return setPaid;
	}

	public Shipping getShipping(){
		return shipping;
	}

	public String getPaymentMethodTitle(){
		return paymentMethodTitle;
	}

	public List<LineItemsItem> getLineItems(){
		return lineItems;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}

	public Billing getBilling(){
		return billing;
	}

	public void setShippingLines(List<ShippingLinesItem> shippingLines) {
		this.shippingLines = shippingLines;
	}

	public void setSetPaid(boolean setPaid) {
		this.setPaid = setPaid;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public void setPaymentMethodTitle(String paymentMethodTitle) {
		this.paymentMethodTitle = paymentMethodTitle;
	}

	public void setLineItems(List<LineItemsItem> lineItems) {
		this.lineItems = lineItems;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public List<CouponsItem> getCouponsItems() {
		return couponsItems;
	}

	public void setCouponsItems(List<CouponsItem> couponsItems) {
		this.couponsItems = couponsItems;
	}
}