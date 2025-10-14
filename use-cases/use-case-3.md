# USE CASE: 3 Produce a report on cities in a given area (globe, continent, region, country, district) from highest population to lowest

## CHARACTERISTIC INFORMATION

### Goal in Context

As a population reporter I want to organise all cities in an area based on largest population to smallest so that I can compare populations of all the cities in that area

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the area is the globe or the specific continent, region, country or district.  Database contains population data of the cities.

### Success End Condition

A report is made for the population reporter to give to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

population reporter

### Trigger

The organisation requires the information of various cities populations in an area.

## MAIN SUCCESS SCENARIO

1. The organisation requests the populations of cities in a given area
2. The population reporter notes the given area
3. The population reporter generates the report on the cities in said area organised by population
4. The population reporter gives the report to the organisation

## EXTENSIONS

3. **Area does not exist**:
    1. population reporter informs the organisation no area exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: 28/11/25