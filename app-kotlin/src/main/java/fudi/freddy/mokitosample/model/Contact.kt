package fudi.freddy.mokitosample.model

import org.codehaus.jackson.annotate.JsonIgnoreProperties

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Contact(var company: String?, var name: String?, var email: String?, var cell: String?,
                   var position: String?, var application: String? = "android",
                   var isFl_app: Boolean = false, var isFl_cot: Boolean = false,
                   var comments: String? = "None")