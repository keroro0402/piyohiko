/**
 * APIステータスコード定義
 *
 * @rules
 * - key は大文字
 */
export const STATES_CODE = {
  OK: 200,
  BAD_REQUEST: 400,
  UNAUTHORIZED: 401, // 認証失敗
  FORBIDDEN: 403, // 権限なし
  NOT_FOUND: 404, // リソースが見つからない
  CONFLICT: 409, // リソースの競合
  INTERNAL_SERVER_ERROR: 500,
} as const;
