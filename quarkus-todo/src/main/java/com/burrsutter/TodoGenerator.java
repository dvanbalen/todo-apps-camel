package com.burrsutter;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import io.quarkus.logging.Log;

public class TodoGenerator implements Processor {

    private int n = 0;
    private List<String> todos = null;

    public TodoGenerator() {
        todos = new ArrayList<String>();
        todos.add(new String("Mow lawn"));
        todos.add(new String("Take out trash"));
        todos.add(new String("Do dishes"));
        todos.add(new String("Make dinner"));
        todos.add(new String("Feed the dog"));
        todos.add(new String("Clean the windows"));
        todos.add(new String("Do laundry"));
        todos.add(new String("Sweep floors"));
        todos.add(new String("Mop floors"));
        todos.add(new String("Dust shelves"));
        todos.add(new String("Mow lawn again"));
        todos.add(new String("Take out trash again"));
        todos.add(new String("Do dishes again"));
        todos.add(new String("Make dinner again"));
        todos.add(new String("Feed the dog again"));
        todos.add(new String("Clean the windows again"));
        todos.add(new String("Do laundry again"));
        todos.add(new String("Sweep floors again"));
        todos.add(new String("Mop floors again"));
        todos.add(new String("Dust shelves again"));

    }

    public void process(Exchange exchange) {
        Todo item = exchange.getIn().getBody(Todo.class);
        Log.info("Current Todo is "+item.title);
        item.completed = false;
        item.order = n;
        item.title = todos.get(n);
        item.url="http://wwww.todos.com/"+n++;
        Log.info("New Todo is "+item.title);
    }
    
}
