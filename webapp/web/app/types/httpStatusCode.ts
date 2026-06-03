/**
 * HttpStatusCode定義
 * 型エイリアスで定義
 */
export const HttpStatusCode = {
  BAD_REQUEST: 400,
  FORBIDDEN: 403,
  NOT_FOUND: 404,
  CONFLICT: 409,
  INTERNAL_SERVER_ERROR: 500,
} as const;

export type HttpStatusCodeType = (typeof HttpStatusCode)[keyof typeof HttpStatusCode];
