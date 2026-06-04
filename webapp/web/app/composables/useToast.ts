import { useToast as useLibToast } from 'vue-toastification';
import { TEXT } from '~/constants/text';
import { STATES_CODE } from '~/constants/api-status';
import type { CustomAxiosError } from '~/types/customAxiosError';

export const useToast = () => {
  const toast = useLibToast();
  const showSuccessToast = (message: string) => {
    toast.success(message, {
      timeout: false,
      closeOnClick: true,
      bodyClassName: 'pre-line-toast',
    });
  };

  const showApiErrorToast = (error: CustomAxiosError) => {
    const statusCode = error?.response?.status;
    const errorMessage = error?.response?.data?.message?.[0];
    if (statusCode === STATES_CODE.BAD_REQUEST) {
      toast.error(errorMessage || TEXT.ERROR.ERROR400, {
        timeout: false,
        closeOnClick: true,
        bodyClassName: 'pre-line-toast',
      });
    } else if (statusCode === STATES_CODE.CONFLICT) {
      toast.error(errorMessage || TEXT.ERROR.ERROR409, {
        timeout: false,
        closeOnClick: true,
        bodyClassName: 'pre-line-toast',
      });
    } else {
      toast.error(TEXT.ERROR.ERROR500, {
        timeout: false,
        closeOnClick: true,
        bodyClassName: 'pre-line-toast',
      });
    }
  };

  return { showSuccessToast, showApiErrorToast };
};
