package java1.algorithms.graph;

import java.util.*;

public class AlienDictionary {

    //BFS(Topological sorting through Kahn's alogirthm):- TC: O(m*n) SC:O(m*n)
    private static String alienOrder(String[] words) {
        if(words == null || words.length == 0) return null;

        Map<Character, List<Character>> adjList = new HashMap<>();
        int[] inDegree = new int[26];

        for(int i=0; i< words.length-1; i++) {
            String currWord = words[i], nextWord = words[i+1];
            if(currWord.length() > nextWord.length() && currWord.substring(0, nextWord.length()).equals(nextWord)) {
                return "";
            }
            for(int j=0; j< Math.min(currWord.length(), nextWord.length()); j++) {
                char ch1 = currWord.charAt(j);
                char ch2 = nextWord.charAt(j);
                if(ch1 != ch2) {
                    if(!adjList.containsKey(ch1)) {
                        adjList.put(ch1, new ArrayList<>());
                    }
                    adjList.get(ch1).add(ch2);
                    inDegree[ch2- 'a']++;
                    break;
                }
            }
        }

        Set<Character> uniqueLetters = new HashSet<>();
        for(String word: words) {
            for(char ch: word.toCharArray()) {
                uniqueLetters.add(ch);
            }
        }

        PriorityQueue<Character> queue = new PriorityQueue<>();
        for(char ch: uniqueLetters) {
            if(inDegree[ch-'a'] == 0) {
                queue.add(ch);
            }
        }

        String topSort = "";
        while(!queue.isEmpty()) {
            char ch = queue.poll();
            topSort += ch;
            if(adjList.get(ch) == null) continue;
            for(char neighbour: adjList.get(ch)) {
                if(--inDegree[neighbour-'a'] == 0) {
                    queue.add(neighbour);
                }
            }
        }
        return topSort.length() == uniqueLetters.size() ? topSort : "";
    }

    public static void main(String[] args) {
        String[] words =  {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(alienOrder(words));
    }
    
}
