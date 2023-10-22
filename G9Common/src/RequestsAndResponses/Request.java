package RequestsAndResponses;

/**
 * 
 * Class description: this enum is for all the responses we get from the client
 * 
 * 
 * @author Obied, Haddad
 * @author Maisalon, Safory
 * @author Ebrahem, Enbtawe
 * @author Mario, Rohana
 * @author Seren ,Hanany.
 * @author Shoroq ,Heib.
 *
 */

public enum Request {

	Connect, Disconnect, LOGIN, POP_UP_ERROR, OPEN_PORTAL, LOGOUT, GET_ITEMS_FROM_DB, GET_PRODUCTS_FROM_DB,
	GET_ORDER_FROM_DB,

	INSER_ORDER_TO_DB, GET_CUSTOMER_DETAILS, INSERT_ORDER_TO_DB, UPDATE_CUSTOMER_BALANCE, CHECK_AMOUNT,
	GET_ITEMS_ACCORDING_TO_COLOR, GET_ITEMS_ACCORDING_TO_TYPE, GET_ITEMS_ACCORDING_TO_PRICE, RESTORE_AMOUNT_FOR_ITEM,
	GET_ALL_ITEMS_FOR_WORKER, REMOVE_ITEM_FROM_CATALOG, UPDATE_ITEM_IN_CATALOG, ADD_ITEM_TO_DB,

	MANAGE_ORDER_FINISHED, UPDATE_BALANCE_ORDER_UNAPPROVED, CANCEL_ORDER_FROM_CUSTOMER_SIDE,
	GET_ORDER_FROM_DB_FOR_CANCELORDER, GET_THE_SUBRACTED_DATE_TIME, THE_SUBRACTED_DATE_TIME_DONE,
	GET_ORDER_FROM_DB_FOR_MANAGER_CANCEL_ORDER, CANCEL_ORDER_FINISHED, UPDATE_WORKER_SALE, UPDATE_PRICES_AFTER_SALES,
	CHECK_IF_CATALOG_EXIST_TO_START_SALES, GET_THE_SALE_PERCENTAGE_FROM_WORKER, UPDATE_WORKER_AFTER_END_SALE,
	UPDATE_PRICES_AFTER_END_SALES, CHECK_IF_SALES_ARE_ON,

	GET_REPORT_FROM_DB, GET_BRANCH_ID, GET_REPORT_Branch, GET_MANAGER_ID, CHECK_REPORT_FROM_DB,
	GET_ORDER_REPORT_FROM_DB, GET_NUM_OF_COMPLAINT, GET_REPORT_FOR_TWO_QUARTERLY, GET_REPORT_FOR_TWO_BRANCHES,
	GET_ORDER_FROM_DB_FOR_DELIVERY, COMPLETED_ORDER_FINISHED, CHECK_REPORT_FROM_DB_FOR_CEO,

	GET_REPORT_FROM_DB_FOR_CEO, UPDATE_WORKER_SALE_FOR_SPECIFIC_BRANCH, CHECK_IF_SALES_ARE_ON_FOR_CATALOG,

	GET_CUSTOMER_FROM_DB, UPDATE_STATUS_CUSTOMER, GET_USER_STATUS, GET_COMPLAINT_FROM_DB, GET_NUM_OF_COMPLAINT_FOR_CEO,
	GET_ORDER_REPORT_FROM_DB_FOR_CEO, UPDATE_BALANCE_AFTER_COMPLAINT, GET_WORKER_BRANCH, GET_ORDER_FROM_DB_FOR_CUSTOMER,

	ADD_COMPLAINT_FROM_USER_TO_DB, GET_USERS_FROM_DB, UPDATE_TYPE_USER, GET_SURVEY_FROM_DB, Upstream, SET_SURVEY_ANSWER,
	GET_USERS_FROM_DB_FOR_WORKER, GET_LAST_COMPLAINT_NUMBER, UPLOAD_PDF_TO_SYSTEM, EXTRACT_PDF_FROM_DB,

	UPLOAD_FILE, GET_SURVEYID, GET_CUSTOMER_EMAIL, CHECK_IF_CUSTOMER_FIRST_ORDER, GET_SURVEY_ID_FROM_DATABASE,

}
