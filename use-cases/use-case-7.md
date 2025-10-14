# USE CASE: 7 Produce a report on the total population of people, people living in cities and people living outside of cities for each continent, region and country

## CHARACTERISTIC INFORMATION

### Goal in Context

As a population reporter I want to see the population of people living in cities and the population of people not living in cities in each continent, region and country so that I can compare the population differences in each area

### Scope

Company.

### Level

Primary task.

### Preconditions

We know to generate continents, regions or countries. Database contains population data for continents, regions, countries and cities.

### Success End Condition

A report is made for the population reporter to give to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

population reporter

### Trigger

The organisation requires the information of various populations of people living in cities in an area.

## MAIN SUCCESS SCENARIO

1. The organisation requests the population of people living in cities for either continent, region or country
2. The population reporter notes the given area type
3. The population reporter generates the report on the population of people living in cities in said area type
4. The population reporter gives the report to the organisation

## EXTENSIONS

3. **Area does not exist**:
    1. population reporter informs the organisation no area exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: 28/11/25