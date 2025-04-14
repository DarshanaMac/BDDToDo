package stepDefinition;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestSteps {

	public static WebDriver driver;
	List<String> visibleTodos;
	

	@Given("I am on the TodoMVC React page")
	public void openTodoPage() {
		WebDriverManager.chromedriver().setup(); // Automatically downloads ChromeDriver v135
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://todomvc.com/examples/react/dist/");
	}

	@When("I enter {string} in the todo input")
	public void enterTodo(String todoText) {
		WebElement input = driver.findElement(By.className("new-todo"));
		input.sendKeys(todoText);
	}

	@When("I press Enter")
	public void pressEnter() {
		WebElement input = driver.findElement(By.className("new-todo"));
		input.sendKeys(Keys.ENTER);
	}

	@Then("I should see {string} in the todo list")
	public void verifyTodoInList(String todoText) {
		List<WebElement> todos = driver.findElements(By.cssSelector(".todo-list li label"));
		boolean found = todos.stream().anyMatch(el -> el.getText().equals(todoText));
		assertTrue(found);
	}

	@Given("I have added a todo {string}")
	public void addTodo(String todoText) {
		openTodoPage();
		enterTodo(todoText);
		pressEnter();
	}

	@When("I mark {string} as completed")
	public void markAsCompleted(String todoText) {
		WebElement checkbox = getCheckboxForTodo(todoText);
		checkbox.click();
	}


	@Then("{string} should appear as completed")
	public void verifyCompleted(String todoText) {
		WebElement todo = getTodoItem(todoText);
		String classAttr = todo.getAttribute("class");
		assertTrue(classAttr.contains("completed"));
	}
	
	@When("I unmark {string} as completed")
    public void unmarkAsCompleted(String todoText) {
        markAsCompleted(todoText); // toggling the checkbox
    }
	
	
	@Given("I have a completed todo {string}")
    public void addCompletedTodo(String todoText) {
        addTodo(todoText);
       markAsCompleted(todoText);
    }
	
	@Then("{string} should appear as active")
    public void verifyActive(String todoText) {
        WebElement todo = getTodoItem(todoText);
        String classAttr = todo.getAttribute("class");
        assertFalse(classAttr.contains("completed"));
    }
	
	@When("I delete the todo {string}")
    public void deleteTodo(String todoText) throws InterruptedException {
        WebElement todoItem = getTodoItem(todoText);
        Actions actions = new Actions(driver);
        actions.moveToElement(todoItem).perform();
        Thread.sleep(4000);
        todoItem.findElement(By.className("destroy")).click();
    }
	
	@Then("{string} should not be visible in the list")
    public void verifyNotVisible(String todoText) {
        List<WebElement> todos = driver.findElements(By.cssSelector(".todo-list li label"));
        boolean found = todos.stream().anyMatch(el -> el.getText().equals(todoText));
        assertFalse(found);
    }
	
	@Given("I have todos {string} and {string} marked as active")
    public void i_have_todos_marked_as_active(String task1, String task2) {
		addTodoTwoItems(task1);
		addTodoTwoItems(task2);
    }
	

    @When("I click on the {string} filter")
    public void i_click_on_the_filter(String filter) {
        List<WebElement> filters = driver.findElements(By.cssSelector(".filters li a"));
        for (WebElement el : filters) {
            if (el.getText().equalsIgnoreCase(filter)) {
                el.click();
                break;
            }
        }
    }

    @Then("I should see {string} and {string}")
    public void i_should_see_and(String task1, String task2) {
        visibleTodos = getVisibleTodos();
        assertTrue("Expected to see: " + task1, visibleTodos.contains(task1));
        assertTrue("Expected to see: " + task2, visibleTodos.contains(task2));
    }

    @And("I should not see {string}")
    public void i_should_not_see(String task) {
        visibleTodos = getVisibleTodos();
        assertFalse("Expected not to see: " + task, visibleTodos.contains(task));
    }

    // ----------- Helper Methods -----------
    
    private WebElement getCheckboxForTodo(String todoText) {
		WebElement item = getTodoItem(todoText);
		return item.findElement(By.className("toggle"));
	}
    
    private WebElement getTodoItem(String todoText) {
		List<WebElement> items = driver.findElements(By.cssSelector(".todo-list li"));
		for (WebElement item : items) {
			WebElement label = item.findElement(By.tagName("label"));
			if (label.getText().equals(todoText))
				return item;
		}
		throw new NoSuchElementException("Todo not found: " + todoText);
	}
    
    public void pause() {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

    private void addTodoTwoItems(String task) {
        WebElement input = driver.findElement(By.className("new-todo"));
        input.sendKeys(task);
        input.sendKeys(Keys.ENTER);
    }

    private void markTodoCompleted(String task) {
        List<WebElement> todos = driver.findElements(By.cssSelector(".todo-list li"));
        for (WebElement el : todos) {
            if (el.getText().contains(task)) {
                el.findElement(By.cssSelector("input.toggle")).click();
                break;
            }
        }
    }

    private List<String> getVisibleTodos() {
        List<String> todos = new ArrayList<>();
        List<WebElement> items = driver.findElements(By.cssSelector(".todo-list li"));
        for (WebElement item : items) {
            todos.add(item.getText());
        }
        return todos;
    }

	

	@Then("I quit from page")
	public void quite() {
		driver.quit();
	}
}
