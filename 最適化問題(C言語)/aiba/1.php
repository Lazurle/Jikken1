<?php
function solve($data) {
    array_unshift($data, [0, 0]);
    $n = count($data);

    // $links[$i] : $data[$i] と接続されているノード番号
    // $data[0] を端点とするためダミーノードと接続済みとして処理する
    $links = array_fill(0, $n, []);
    $links[0][0] = PHP_INT_MAX;

    // $edges[$i] :
    //   $data[$i] の次数が 0 (未接続) の場合は $i
    //   $data[$i] の次数が 1 (端点) の場合は反対側の端点のノード番号
    //   $data[$i] の次数が 2 (中間点) の場合は -1
    $edges = range(0, $n - 1);
    $edges[0] = PHP_INT_MAX;

    // $distances : ノード間の距離を保持する二次元配列
    //   $distances[*][0] 一方のノード番号
    //   $distances[*][1] もう一方のノード番号
    //   $distances[*][2] ノード間の距離
    $distances = make_distances($data);
    usort($distances, function ($a, $b) { return $a[2] <=> $b[2]; });
    foreach ($distances as [$i, $j, $distance]) {
        connect($data, $i, $j, $edges, $links);
    }

    return build_answer($data, $links);
}

function make_distances($data) {
    $distances = [];
    $n = count($data);
    for ($i = 0; $i < $n; ++$i) {
        for ($j = $i + 1; $j < $n; ++$j) {
            $distances[] = [$i, $j, distance($data[$i], $data[$j])];
        }
    }
    return $distances;
}

function connect($data, $i, $j, &$edges, &$links) {
    // 既存の経路の中間点から枝分かれしてはいけない
    if ($edges[$i] == -1 || $edges[$j] == -1) return;
    // 経路の両端を繋いでループにしてはいけない
    if ($edges[$i] == $j || $edges[$j] == $i) return;

    // $i と $j が繋がるので "$i の反対端 ($ei)" の反対端は "$j の反対
    端 ($ej)" になる ($j も同様)
    $ei = $edges[$i];
    $ej = $edges[$j];
    $edges[$ei] = $ej;
    $edges[$ej] = $ei;
    // この接続により $i が中間点になる場合を考慮する ($j も同様)
    // $i が未接続だった場合は $i == $ei なので手前の処理で正しく更新
    済み
    if ($ei != $i) $edges[$i] = -1;
    if ($ej != $j) $edges[$j] = -1;

    $links[$i][] = $j;
    $links[$j][] = $i;
}

function build_answer($data, $links) {
    $answer = [];
    $prev = PHP_INT_MAX;
    $curr = 0;
    while (count($links[$curr]) == 2) {
        if (($next = $links[$curr][0]) == $prev) $next =
    $links[$curr][1];
        $prev = $curr;
        $curr = $next;
        $answer[] = $data[$curr];
    }
    return $answer;
}
