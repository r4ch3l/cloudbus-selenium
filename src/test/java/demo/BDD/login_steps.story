Narrative: User Sign from home page.


Scenario: User sign in with right password
When User open Home page click sign in link
When User enter valid email as: 'zxiong@tibco-support.com' password as: 'findbear' and submit.
Then User should see home page should be reloaded and There is a username as: 'Tom Zhao'

