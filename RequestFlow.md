# Introduction #
> このプロジェクトでは以下のようなリクエストフロー(各階層の呼び名)と定義します。
    1. HTML DOM _(View Layer)_
      1. JSP / XHTML
    1. HTTP post / get _(Request Layer)_
      1. HTML Form Submit / URL direct / javascript-Ajax submit
    1. Service Provider _(Interface Layer)_
      1. Servlet filter
      1. Protocol Converter
    1. Business Service _(Business Process Layer)_
      1. Business Service Interceptor
      1. Business Service
    1. Business Component _(Business Logic Layer)_
      1. Business Component Interceptor
      1. Business Component
    1. Data Access Object _(Persistence Layer)_
      1. O/R Mapper
      1. DAO
    1. Database / File _(Model Layer)_

# Details #