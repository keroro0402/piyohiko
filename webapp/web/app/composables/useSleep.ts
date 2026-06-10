/**
 * 指定された時間だけ処理を一時停止（スリープ）させる関数
 * * @param ms 待機する時間（ミリ秒）
 * @returns 待機完了後に解決されるPromiseオブジェクト
 */
export const sleep = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms));
