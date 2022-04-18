# RecipeApp V2


## SHORT EXPLENATION
A very basic cookingRecipe-management-application. Allows one to manage their **most loved recipes** including ingredients and instructions

## EXISTING USER FEATURES
- crud-menu to manage recipes
- listing of all, and specific recipes
- sub-crud-menu to manage ingredients of chosen recipe
- advanced listing options with descriptions and ingredients
- persistent saving with xml and json

## PLANED USER FEATURES
- additional sorting/recalculating of amount of ingredients, based on their units 

## DEVELOPER FEATURES
- implemented kdoc
- jaccoco
- *"fat"* jar
- ktlint
- JUnit Tests

## GITHUB FEATURES
- **this Readme**, constantly updated with every new Version
- a small **wiki** explaining possible user-input choices for the application 

## REQUIREMENTS
- kotlin support
- console to interact with

## EXTERNAL CODE
- **Dokka DEPENDENCY:** implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.6.10")
- **Dokka PLUGIN:** id("org.jetbrains.dokka") version "1.6.10"
- **ktlint PLUGING:** id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
- **XStream DEPENDENCY:** implementation("com.thoughtworks.xstream:xstream:1.4.19")
- **Jettison DEPENDENCY:**implementation("org.codehaus.jettison:jettison:1.4.1")
