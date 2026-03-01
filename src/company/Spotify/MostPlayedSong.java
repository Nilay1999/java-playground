package company.Spotify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class TopKSongs {
    public static class Song {
        public String songId;
        public long timestamp;

        public Song(String songId, long timestamp) {
            this.songId = songId;
            this.timestamp = timestamp;
        }
    }

    List<Song> playedSongs = new ArrayList<>();

    public TopKSongs() {
    }

    public void play(String songId, long timestamp) {
        playedSongs.add(new Song(songId, timestamp));
    }

    public List<String> getTopK(int k, long currentTime) {
        List<String> result = new ArrayList<>();
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        Map<String, Integer> map = new HashMap<>();
        for (Song song : playedSongs) {
            if (song.timestamp >= currentTime - 3600 && song.timestamp <= currentTime) {
                map.put(song.songId, map.getOrDefault(song.songId, 0) + 1);
            }
        }

        pq.addAll(map.entrySet());
        int i = 0;
        while (i < k) {
            if (!pq.isEmpty()) {
                Map.Entry<String, Integer> entry = pq.poll();
                result.add(entry.getKey());
                i++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TopKSongs tracker = new TopKSongs();
        tracker.play("Blinding Lights", 1000);
        tracker.play("Shape of You", 1200);
        tracker.play("Blinding Lights", 1500);
        tracker.play("Levitating", 2000);
        tracker.play("Blinding Lights", 2500);
        tracker.play("Shape of You", 3000);
        tracker.play("Bad Guy", 3500);
        tracker.play("Blinding Lights", 4000);
        tracker.play("Levitating", 4200);

        List<String> top3 = tracker.getTopK(3, 4500);
        System.out.println(top3);
    }
}