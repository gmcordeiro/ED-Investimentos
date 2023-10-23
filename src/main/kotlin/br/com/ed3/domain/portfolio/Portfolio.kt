/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package br.com.ed3.domain.portfolio

import br.com.ed3.domain.assets.FinancialAssets
import com.squareup.moshi.Json
import java.util.*

/**
 * 
 *
 * @param id 
 * @param description 
 * @param financialAssets 
 */


data class Portfolio (

    @Json(name = "id")
    val id: java.util.UUID = UUID.randomUUID(),

    @Json(name = "description")
    val description: kotlin.String,

    @Json(name = "financialAssets")
    val financialAssets: kotlin.collections.List<FinancialAssets>

)

