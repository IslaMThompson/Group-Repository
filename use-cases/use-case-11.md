# USE CASE: 11 Produce a report with the information of a country (code, name, continent, region, surface_area, indep_year, population, life_expectancy, gnp, gnp_old, local_name, government_form, state_head, capital, code2)

## CHARACTERISTIC INFORMATION

### Goal in Context

As a population reporter I want to be able to produce a generate a report on a country with all it's information so I can review information on different countries

### Scope

Company.

### Level

Primary task.

### Preconditions

Database contains information on countries

### Success End Condition

A report is made for the population reporter to give to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

population reporter

### Trigger

The organisation requires a report with a countries information

## MAIN SUCCESS SCENARIO

1. The organisation requests a report with a countries information
2. The population reporter generates the report
3. The population reporter gives the report to the organisation

## EXTENSIONS

3. **Country does not exist**:
    1. population reporter informs the organisation no country exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: 28/11/25