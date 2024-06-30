# Payroll Project

## Employee API

## Create Employee
Endpoint : POST /api/employee

because every employee can get many payroll based on dated

request body
```json 
    {
        "name" : "barry",
        "gender" : "male",
        "grade" : 2,
        "isMarried" : false
    }

```
response succes
```json 
    {
      "id_emp": 1,
      "name" : "barry",
      "gender" : "male",
      "grade" : 2,
      "isMarried" : false
}
```
## Get Employee
Endpoint : GET /api/employee

response
```json
{
  "data": [
    {
      "id_emp": 1,
      "name" : "barry",
      "gender" : "male",
      "grade" : 1,
      "isMarried" : false
    },
    {
      "id_emp": 1,
      "name" : "barry",
      "gender" : "male",
      "grade" : 1,
      "isMarried" : false
    }
  ]
}
```
    
## Payroll API

## Add Payroll
Endpoint POST /api/payroll/{id_emp} 

request body 

```json
   {
    "daysPresent" : 20,
    "daysAbsent" : 2,
    "period" : "2023-12-01"
    }
```
response success
```json
    {
  "data": {
    "id_emp": 1,
    "name": "barry",
    "gender": "male",
    "grade": 2,
    "isMarried": true,
    "payroll": {
      "id_payroll": "iqu1o314134",
      "basic_salary": 9000000,
      "paycut": 2000000,
      "allowance": 6000000,
      "head_of_family" : 2000000,
      "period": "2023-12-01"
    }
  }
}
```
## Get Payroll by Id Employee
Endpoint GET /api/payroll/{id_emp}
```json
    {
  "data": {
    "id_emp": 1,
    "name": "barry",
    "gender": "male",
    "grade": 2,
    "isMarried": true,
    "payroll": {
      "id_payroll": "iqu1o314134",
      "basic_salary": 9000000,
      "paycut": 2000000,
      "allowance": 6000000,
      "head_of_family" : 2000000,
      "period": "2023-12-01"
    },
      "id_payroll": "a848s6fa067",
      "basic_salary": 9000000,
      "paycut": 1000000,
      "allowance": 6000000,
      "head_of_family" : 2000000,
      "period": "2024-01-01"
  }
}

```

## Matrix Salary

## Get Matrix Salary
Endpoint GET /api/matrixsalary
```json
[
  {
    "id_salary": "lvl1",
    "grade" : 1,
    "basic_salary" : 5000000,
    "paycut" : 100000,
    "allowance" : 150000,
    "head_of_family" : 1000000
  },
  {
    "id_salary": "lvl2",
    "grade" : 2,
    "basic_salary" : 9000000,
    "paycut" : 200000,
    "allowance" : 300000,
    "head_of_family" : 2000000
  },
  {
    "id_salary": "lvl3",
    "grade" : 3,
    "basic_salary" : 15000000,
    "paycut" : 400000,
    "allowance" : 600000,
    "head_of_family" : 3000000
  }
]



```