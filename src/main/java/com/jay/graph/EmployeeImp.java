package com.jay.graph;

import java.util.*;

public class EmployeeImp {

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    public int getImportance(List<Employee> employees, int id) {

        HashMap<Integer, Employee> org = new HashMap<>();

        for (var emp : employees) {
            org.put(emp.id, emp);
        }

        int res = 0;
        HashSet<Integer> visited = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);

        while (!queue.isEmpty()) {

            var eid = queue.poll();
            if (visited.contains(eid))
                continue;
            visited.add(eid);

            var employee = org.get(eid);
            res += employee.importance;

            for (var subordinate : employee.subordinates) {
                if (!visited.contains(subordinate))
                    queue.offer(subordinate);
            }
        }

        return res;
    }
}
