PART2
In this part, we answer questions like “Who created …”
First, we constructed queries to query MQLRead API. 
For business funder:
[{
  "/organization/organization_founder/organizations_founded": [{
    "a:name": null,
    "name~=": "google"
  }],
  "id": null,
  "name": null,
  "type": "/organization/organization_founder"
}]

For author:
[{
  "/book/author/works_written": [{
    "a:name": null,
    "name~=": “google”
  }], 
  "id": null,
  "name": null,
  "type": "/book/author"
}]
For each query, we query both of the two queries, and get JSON file from MQLRead API.
After extracting creator’s name and the related created things, we order them by creator’s name in alphabetic order. To do the order, we first sort the names, then construct a list according to the list of sorted names with the value to be the list of created things. Then we print all the information.

Main:
The input parameters strictly follow the format of the sample program. Specifically, we print help and exit the program is key is not properly given. Then, we only run infobox or question if -t infobox|question is specified. After information is printed, the program will close. If format except first two parameters that gives key information is not right, or we just have two parameters about the key, the program enters the explore mode, where user can continuously query the program and result printed. User need to use control+c to exit.
