package company.Spotify;

import java.util.*;

public class PlaylistShuffle {

    public static class Music {
        public int songId;
        public String artist;
        public int freq;

        public Music(int songId, String artist, int freq) {
            this.songId = songId;
            this.artist = artist;
            this.freq = freq;
        }
    }

    public List<Music> shuffleMusic(Music[] songs) {
        // Map: artist -> list of songs by that artist
        Map<String, Queue<Music>> artistToSongs = new HashMap<>();

        // Group songs by artist
        for (Music song : songs) {
            artistToSongs.putIfAbsent(song.artist, new LinkedList<>());
            artistToSongs.get(song.artist).add(song);
        }

        // Max heap: compare by number of remaining songs for each artist
        PriorityQueue<Map.Entry<String, Queue<Music>>> pq = new PriorityQueue<>(
                (a, b) -> b.getValue().size() - a.getValue().size());

        pq.addAll(artistToSongs.entrySet());

        List<Music> result = new ArrayList<>();
        Map.Entry<String, Queue<Music>> prev = null;

        while (!pq.isEmpty()) {
            // Pick artist with most remaining songs
            Map.Entry<String, Queue<Music>> current = pq.poll();
            result.add(current.getValue().poll()); // Add one song from this artist

            // Add previous artist back if they have more songs
            if (prev != null && !prev.getValue().isEmpty()) {
                pq.offer(prev);
            }

            prev = current;
        }

        return result;
    }

    public static void main(String[] args) {
        Music[] arr = new Music[7];

        arr[0] = new Music(1, "Ed Sheeran", 0);
        arr[1] = new Music(2, "Ed Sheeran", 0);
        arr[2] = new Music(3, "Eminem", 0);
        arr[3] = new Music(4, "Drake", 0);
        arr[4] = new Music(5, "Adele", 0);
        arr[5] = new Music(6, "Taylor Swift", 0);
        arr[6] = new Music(7, "Taylor Swift", 0);

        PlaylistShuffle playlist = new PlaylistShuffle();
        List<Music> result = playlist.shuffleMusic(arr);

        for (Music music : result) {
            System.out.println(music.artist);
        }
    }
}