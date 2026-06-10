/**
 * CustomAxiosError定義
 * 型エイリアスで定義
 */

import type { AxiosError } from 'axios';
import type { ApiErrorResponse } from '~/types/apiErrorResponse';

export type CustomAxiosError = AxiosError<ApiErrorResponse>;
