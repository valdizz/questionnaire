package com.valdizz.myapplication.model.network

import okhttp3.*

class FakeInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return Response.Builder()
            .code(200)
            .message(QUESTIONNAIRE_JSON.trimIndent())
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(ResponseBody.create(MediaType.parse("application/json"), QUESTIONNAIRE_JSON.toByteArray()))
            .addHeader("content-type", "application/json")
            .build()
    }

    private companion object {

        const val QUESTIONNAIRE_JSON = """
{
    "questionnaire":{
        "stepList":[
            {
                "groupList":[
                    {
                        "displayName":"Персональные данные заявителя",
                        "id":"CLIENT_PERSONAL_DATA_FOR_CONFIRMATION",
                        "questionList":[
                            {
                                "displayName":"Дата рождения",
                                "editable":"1",
                                "id":"BIRTHDATE",
                                "mandatory":"1",
                                "pattern":"yyyy-MM-dd",
                                "type":"DATE"
                            },
                            {
                                "displayName":"Фамилия",
                                "editable":"1",
                                "id":"LASTNAME",
                                "mandatory":"1",
                                "type":"TEXT"
                            },
                            {
                                "displayName":"Имя",
                                "editable":"1",
                                "id":"FIRSTNAME",
                                "mandatory":"1",
                                "type":"TEXT"
                            },
                            {
                                "displayName":"Отчество",
                                "editable":"1",
                                "id":"MIDDLENAME",
                                "mandatory":"1",
                                "type":"TEXT"
                            },
                            {
                                "displayName":"Мобильный телефон",
                                "editable":"1",
                                "id":"PHONEMOBILE",
                                "mandatory":"0",
                                "type":"PHONE"
                            }
                        ],
                        "visible":"1"
                    },
                    {
                        "displayName":"Документ, удостоверяющий личность",
                        "id":"CLIENT_DOCUMENT_DATA_FOR_CONFIRMATION",
                        "questionList":[
                            {
                                "displayName":"Номер удостоверения личности",
                                "editable":"1",
                                "id":"CERTNUM",
                                "mandatory":"1",
                                "type":"TEXT"
                            },
                            {
                                "displayName":"Дата удостоверения личности",
                                "editable":"1",
                                "id":"CERTDATE",
                                "mandatory":"1",
                                "pattern":"yyyy-MM-dd",
                                "type":"DATE"
                            },
                            {
                                "displayName":"Срок действия удостоверения личности",
                                "editable":"1",
                                "id":"CERTEXPIREDATE",
                                "mandatory":"1",
                                "pattern":"yyyy-MM-dd",
                                "type":"DATE"
                            },
                            {
                                "displayName":"Орган выдавший удостоверение личности",
                                "editable":"1",
                                "id":"CERTPUBLICAUTH",
                                "mandatory":"1",
                                "type":"TEXT"
                            }
                        ],
                        "visible":"1"
                    },
                    {
                        "displayName":"Место регистрации",
                        "id":"ADDRESS_REG_RO",
                        "questionList":[
                            {
                                "displayName":"Населённый пункт",
                                "editable":"1",
                                "id":"SETTLEMENTNAME",
                                "mandatory":"1",
                                "type":"TEXT"
                            },
                            {
                                "displayName":"Улица",
                                "editable":"1",
                                "id":"STREETNAME",
                                "mandatory":"1",
                                "type":"TEXT"
                            },
                            {
                                "displayName":"Дом",
                                "editable":"1",
                                "id":"HOUSENUM",
                                "mandatory":"1",
                                "type":"TEXT"
                            },
                            {
                                "displayName":"Корпус",
                                "editable":"1",
                                "id":"CORPSNUM",
                                "mandatory":"0",
                                "type":"TEXT"
                            },
                            {
                                "displayName":"Квартира",
                                "editable":"1",
                                "id":"OFFICENUM",
                                "mandatory":"0",
                                "type":"NUMBER"
                            }
                        ],
                        "visible":"1"
                    },
                    {
                        "displayName":"Сумма и срок кредитования",
                        "id":"AMOUNT_AND_PERIOD",
                        "questionList":[
                            {
                                "displayName":"Срок кредитования",
                                "editable":"1",
                                "id":"PERIOD_CREDIT",
                                "mandatory":"1",
                                "type":"NUMBER",
                                "min":"12",
                                "max":"36"
                            },
                            {
                                "displayName":"Сумма",
                                "editable":"1",
                                "id":"AMOUNT_CREDIT",
                                "mandatory":"1",
                                "type":"AMOUNT",
                                "min":"100",
                                "max":"1000000"
                            }
                        ],
                        "visible":"1"
                    },
                    {
                        "id":"AGREEMENTS_FOR_CONFIRMATION",
                        "questionList":[
                            {
                                "displayName":"Подтверждаю корректность и актуальность клиентских данных",
                                "editable":"1",
                                "id":"ISAGCLIENTDATA",
                                "mandatory":"1",
                                "max":"true",
                                "min":"true",
                                "type":"BOOLEAN"
                            }
                        ],
                        "visible":"1"
                    }
                ],
                "visible":"1"
            }
        ]
    }
}
        """
    }
}