Feature: TemplateChooser


    @templateChooser
    Scenario: Opens The application
        Given I am a user
        Then I should see a 'Go To TemplateChooser' button
        Then I should see SlidingViewPager View