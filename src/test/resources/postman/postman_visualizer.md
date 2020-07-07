# A few thoughts on Postman Visualizer #
The Postman Visualizer is a new feature within Postman and was fully released at the end of 2019.
>Postman provides a programmable way to visually represent your request responses.

## Short introduction ##
This is just supposed to be a summary and a short introduction. For an in-depth introduction, API documents and examples visit the [Postman Website](https://learning.postman.com/docs/postman/sending-api-requests/visualizer/).
The Visualizer uses HTML, CSS and Javascript to create the visualizations.

The code for the visualization can be written in either the *Pre-request Script* or the *Test*.  
We first need to create a template. This is where most of the "magic" happens. We pretty much create an actual website with HTML, CSS and Javascript - without the `<hmtl>`, `<head>` and `<body>` tags. Since we are using template literals make sure to use backticks like in the example from the Website:

    var template = `
        <table bgcolor="#FFFFFF">
            <tr>
                <th>Name</th>
                <th>Email</th>
            </tr>

            {{#each response}}
                <tr>
                    <td>{{name}}</td>
                    <td>{{email}}</td>
                </tr>
            {{/each}}
        </table>
    `;

We then set the template and the response from the chosen request to the visualizer utilizing the ***pm.visualizer.set()*** method:

    // Set visualizer
    pm.visualizer.set(template, {
        // Pass the response body parsed as JSON as `data`
        response: pm.response.json()
    });

To pass response data to the template, use
* handlebars for HTML Elements
* the ***pm.getData(callback)*** for Javascript variables within the `<script>`


## Pros and Cons ##
### Pros: ###
* visualization shown directly in the Postman App (under Visualize)
* Developer tools to inspect the code (also console.log etc.)
* Received data is way more readable
* Possible to utilize Charts.js, Bootstrap, etc.
* Display only the wanted properties
* some visualization templates available from the community
* a well-written template can be used for any kind of received Object
* visualization can be shared though sharing the collection

### Cons: ###
* only works within Postman app
* no implementation with **Newman**
* code must be written for each request
* only able to visualize one request (not entire collection)
* in the template literal no coloring and other IDE features
* to update the visualization after a change in the code the request must be resend
* no postman specific way to integrate other data e.g. from csv
* can't save visualization or export html

## Evaluation ##
The Postman Visualizer is a fairly new feature within Postman. Since the code for the visualization, the output and the related request are all within the App, it can be a useful tool for rapid prototyping. One can test the API and also test possible GUI implementations. The ability of sharing a collection makes it easy to work on them with others.  
It is important to note though: Even though the code is usually created within the "Test" tab, the *visualizer is not meant for visualizing actual test* runs, because every visualization is written for one request. There is no way to visualize the whole collection. When running the whole collection the visualization is simply skipped / not shown. It's theoretically possible to use results from *pm.test()*, but Postman already has a somewhat graphic display for that under the *"Test Results"* tab.
Because the Visualizer is not somehow usable within *Newman*, it also can't be used when utilizing *Continuous Integration* with Newman in e.g. *Jenkins*. While researching the topic I've found a possibly interesting alternative: [HtmlExtra](https://www.npmjs.com/package/newman-reporter-htmlextra)  
This is a Newman reporter which displays test results. It's possible to get an Overview of the tests as well as Details about every test.

## Examples ##
* You can check out the [collections created by the community](https://explore.postman.com/templates/search?q=visualizer)
* View the [snapshot of my collection](https://www.getpostman.com/collections/a65cfbc38bbd2bb20e09) which is mostly designed for this *todo-service API*
