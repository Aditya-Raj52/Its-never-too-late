class Solution:
    def countCompleteComponents(self, n: int, edges: List[List[int]]) -> int:# Step 1: Build the graph using an adjacency list
        adj = [[] for _ in range(n)]
        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)
        
        visited = [False] * n
        complete_count = 0
        
        # Step 2: Iterate through all vertices to find connected components
        for i in range(n):
            if not visited[i]:
                # Collect all nodes belonging to the current component
                component = []
                stack = [i]
                visited[i] = True
                
                while stack:
                    curr = stack.pop()
                    component.append(curr)
                    for neighbor in adj[curr]:
                        if not visited[neighbor]:
                            visited[neighbor] = True
                            stack.append(neighbor)
                
                # Step 3: Verify if this component is "complete"
                v_count = len(component)
                is_complete = True
                
                for node in component:
                    # Each node must be connected to exactly (total nodes - 1) others
                    if len(adj[node]) != v_count - 1:
                        is_complete = False
                        break
                
                if is_complete:
                    complete_count += 1
                    
        return complete_count
        